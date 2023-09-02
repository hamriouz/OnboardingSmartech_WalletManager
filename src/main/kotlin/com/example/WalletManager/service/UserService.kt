package com.example.WalletManager


import com.example.WalletManager.model.FullUser
import com.example.WalletManager.model.Transaction
import com.example.WalletManager.model.SetOrChangeName
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/Wallet/User")
interface UserService {
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): FullUser

    @PostMapping("/Create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewUser(@RequestBody userName: SetOrChangeName ): String

    @PutMapping("/Transaction")
    @ResponseStatus(HttpStatus.OK)
    fun transaction(@RequestBody transaction: Transaction): Transaction = transaction

    @PutMapping("/Rename/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun renameUser(@RequestBody newName: SetOrChangeName, @PathVariable id: Int): String

}

