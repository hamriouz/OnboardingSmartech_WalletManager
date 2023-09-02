package com.example.WalletManager.model

import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy
import javax.persistence.*
import java.lang.annotation.*;
import java.lang.reflect.Constructor
@Entity
@Table(name = "users")
class User() {
    constructor(name: String) : this() {
        this.ownerName = name
    }

    @Id
    @GeneratedValue
    var id: Int? = null

    @Column(name = "owner_name")
    var ownerName: String? = null

}