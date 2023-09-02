package com.example.WalletManager.service

import com.example.WalletManager.datasource.UserDataSource
import com.example.WalletManager.model.User
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class UserServiceTest {
    private val dataSource: UserDataSource = mockk()
    private val userService = UserService(dataSource)
    @Test
    fun `should call its data source to retrieve users`() {

//        val users = UserService.getUsers()

        
        
    }
}