package com.example.WalletManager.service

import com.example.WalletManager.UserService
import com.example.WalletManager.model.*
import com.example.WalletManager.repository.UserRepository
import com.example.WalletManager.repository.WalletRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImp(
    private var userDB: UserRepository,
    private var walletDB: WalletRepository,
) : UserService{

    override fun getUserById(id: Int): FullUser {
        val user =  userDB.findById(id)
        val wallet = walletDB.findByUserId(id)
        return FullUser(id, user.get().ownerName, wallet?.balance)
    }

    override fun createNewUser(userName: SetOrChangeName): String {
        val user = userDB.save(User(name = userName.name))
        val userId = user.id
        val wallet = walletDB.save(Wallet(userId, 0))
        return "User created with id $userId"
    }

    override fun transaction(transaction: Transaction): Transaction {
        val user = userDB.findById(transaction.userId)
        val wallet = walletDB.findByUserId(transaction.userId)
        return transaction
    }

    override fun renameUser(newName: SetOrChangeName, id: Int): String {
        val user = userDB.findById(id)
        user.get().ownerName = newName.name
        userDB.save(user.get())
        val name = newName.name
        return "User's name with userid $id was successfully changed to $name"
    }


}

