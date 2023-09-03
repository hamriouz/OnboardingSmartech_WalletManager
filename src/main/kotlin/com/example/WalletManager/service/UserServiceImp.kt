package com.example.WalletManager.service

import com.example.WalletManager.UserService
import com.example.WalletManager.kafka.KafkaProducer
import com.example.WalletManager.model.*
import com.example.WalletManager.repository.UserRepository
import com.example.WalletManager.repository.WalletRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImp(
    private val userDB: UserRepository,
    private val walletDB: WalletRepository,
    private val kafkaProducer: KafkaProducer,
) : UserService{

    override fun getUserById(id: Int): FullUser {
        val user =  userDB.findById(id)
        val wallet = walletDB.findByUserId(id)
        return FullUser(id, user.get().ownerName, wallet?.balance)
    }

    override fun createNewUser(userName: SetOrChangeName): String {
        val user = userDB.save(User(name = userName.name))
        val userId = user.id
        walletDB.save(Wallet(userId, 0))
        return "User created with id $userId"
    }

    override fun transaction(transaction: Transaction): String {
        val kafkaMessage = createKafkaMessage(transaction)
        kafkaProducer.sendEvent(kafkaMessage)
        return "The transaction was successfully added to the transaction queue"
    }

    private fun createKafkaMessage(transaction: Transaction): String {
        val userid = transaction.userId
        val amount = transaction.amount
        val transactionType = transaction.transactionType
        return "userid=$userid:amount=$amount:transactionType=$transactionType"
    }

    fun makeTransaction(transaction: Transaction): TransactionStatus {
        val wallet = walletDB.findByUserId(transaction.userId)
        if (transaction.transactionType == TransactionType.DECREASE) {
            if (wallet!!.balance!! < transaction.amount) {
                return TransactionStatus.NOT_ENOUGH_BALANCE
            }
            wallet.balance  = wallet.balance?.minus(transaction.amount)
            walletDB.save(wallet)
            return TransactionStatus.SUCCESSFUL
        }
        wallet!!.balance = wallet.balance?.plus(transaction.amount)
        walletDB.save(wallet)
        return TransactionStatus.SUCCESSFUL
    }

    override fun renameUser(newName: SetOrChangeName, id: Int): String {
        val user = userDB.findById(id)
        user.get().ownerName = newName.name
        userDB.save(user.get())
        val name = newName.name
        return "User's name with userid $id was successfully changed to $name"
    }
}
