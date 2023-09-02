package com.example.WalletManager.service

import com.example.WalletManager.UserService
import com.example.WalletManager.model.Transaction
import com.example.WalletManager.model.User
import com.example.WalletManager.model.SetOrChangeName
import com.example.WalletManager.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImp(
    private var db: UserRepository
) : UserService{

    override fun getUserById(id: Int): Optional<User> {
        return db.findById(id)
    }

    override fun createNewUser(userName: SetOrChangeName): String {
        val user = db.save(User(name = userName.name))
        val userId = user.id
//        todo create a wallet and set its userid
        return "User created with id $userId"
    }

    override fun transaction(transaction: Transaction): Transaction {
        println(transaction)
        println(transaction.userId)
        println(transaction.transactionType)
        println(transaction.amount)
        return transaction
    }

    override fun renameUser(newName: SetOrChangeName, id: Int): String {
        val user = db.findById(id)
        user.get().ownerName = newName.name
        db.save(user.get())
        val name = newName.name
        return "User's name with userid $id was successfully changed to $name"
    }


}

