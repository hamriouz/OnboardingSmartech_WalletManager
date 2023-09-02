package com.example.WalletManager.datasource.mock

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

internal class MockUserDataSourceTest {
    private val mockDataSource = MockUserDataSource()

    @Test
    fun `should provide a collection of banks`() {
        val banks = mockDataSource.retrieveUsers()

        assertThat(banks).isNotEmpty()

    }
    
    @Test
    fun `should provide some mock data`() {
        val users = mockDataSource.retrieveUsers()
        assertThat(users).allMatch { it.owner.isNotBlank()}
        assertThat(users).allMatch { it.balance >= 0}
        
        
        
    }
}