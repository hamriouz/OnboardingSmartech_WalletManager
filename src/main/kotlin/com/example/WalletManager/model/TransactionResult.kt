package com.example.WalletManager.model

import org.apache.kafka.common.utils.Time
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.Date
import javax.persistence.Id


@Document(indexName = "transaction_result", shards = 256)
class TransactionResult(isSuccessful: Boolean, userId: Int, failureReason: String) {

    @Id
    var id: Int? = null

    @Transient
    var isSuccessful: Boolean? = isSuccessful

    @Field(name = "user_id", type = FieldType.Integer, storeNullValue = false)
    var userId: Int? = userId

    @Transient
    var failureReason: String? = failureReason

}