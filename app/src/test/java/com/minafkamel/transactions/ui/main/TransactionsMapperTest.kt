package com.minafkamel.transactions.ui.main

import com.minafkamel.transactions.R
import com.minafkamel.transactions.createAmount
import com.minafkamel.transactions.createTransactionsRaw
import com.minafkamel.transactions.data.entities.TransactionsRaw
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionsMapperTest {

    private lateinit var mapper: TransactionsMapper

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Before
    fun setUp() {
        mapper = TransactionsMapper()
    }

    @Test
    fun transactionsRawIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: transactionsRaw is null")

        mapper.toTransactionsViewEntities(null)
    }

    @Test
    fun transactionsIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: transactions is null")

        mapper.toTransactionsViewEntities(TransactionsRaw(null))
    }

    @Test
    fun nameIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: name is null")

        mapper.toTransactionsViewEntities(createTransactionsRaw(name = null))
    }

    @Test
    fun timestampIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: timestamp is null")

        mapper.toTransactionsViewEntities(createTransactionsRaw(timesStamp = null))
    }

    @Test
    fun amountIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: amount is null")

        mapper.toTransactionsViewEntities(createTransactionsRaw(amount = null))
    }

    @Test
    fun valueIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: value is null")

        mapper.toTransactionsViewEntities(createTransactionsRaw(amount = createAmount(value = null)))
    }

    @Test
    fun currencyIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: currency is null")

        mapper.toTransactionsViewEntities(createTransactionsRaw(amount = createAmount(currency = null)))
    }

    @Test
    fun categoryIsNull_ThrowsException() {
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("TransactionsMapper: category is null")

        mapper.toTransactionsViewEntities(createTransactionsRaw(category = null))
    }

    @Test
    fun categoryIconNull_ShouldShowDefaultImageIsTrueAndImageUrlIsEmpty() {
        val expectedShouldShowDefaultImage = true
        val expectedImageUrl = ""

        val entities = mapper.toTransactionsViewEntities(createTransactionsRaw(categoryIcon = null))

        assertEquals(expectedShouldShowDefaultImage, entities.first().image.shouldShowDefaultImage)
        assertEquals(expectedImageUrl, entities.first().image.imageUrl)
    }

    @Test
    fun categoryIconIsEmpty_ShouldShowDefaultImageIsTrueAndImageUrlIsEmpty() {
        val expectedShouldShowDefaultImage = true
        val expectedImageUrl = ""

        val entities = mapper.toTransactionsViewEntities(createTransactionsRaw(categoryIcon = ""))

        assertEquals(expectedShouldShowDefaultImage, entities.first().image.shouldShowDefaultImage)
        assertEquals(expectedImageUrl, entities.first().image.imageUrl)
    }


    @Test
    fun categoryIconIsNotNullOrEmpty_MappedToImageUrlAndShouldShowDefaultImageIsFalse() {
        val expectedShouldShowDefaultImage = false
        val expectedImageUrl = "categoryIcon"

        val entities =
            mapper.toTransactionsViewEntities(createTransactionsRaw(categoryIcon = "categoryIcon"))

        assertEquals(expectedImageUrl, entities.first().image.imageUrl)
        assertEquals(expectedShouldShowDefaultImage, entities.first().image.shouldShowDefaultImage)
    }

    @Test
    fun nameMappedToTitle() {
        val expectedTitle = "name"

        val entities = mapper.toTransactionsViewEntities(createTransactionsRaw(name = "name"))

        assertEquals(expectedTitle, entities.first().title)
    }


    @Test
    fun categoryMappedToCategory() {
        val expectedCategory = "category"

        val entities =
            mapper.toTransactionsViewEntities(createTransactionsRaw(category = "category"))

        assertEquals(expectedCategory, entities.first().category)
    }

    @Test
    fun formatsDateAndAddsPrefix() {
        val formattedDate = "09/01/2020"

        val entities =
            mapper.toTransactionsViewEntities(createTransactionsRaw(timesStamp = "2020-01-09 14:46:33"))

        assertEquals(formattedDate, entities.first().date)
    }


    @Test
    fun valueIsPositive_AmountMappedWithPlusAndCurrency() {
        val expectedAmount = "+12.34 EUR"

        val entities =
            mapper.toTransactionsViewEntities(
                createTransactionsRaw(
                    amount = createAmount(
                        1234.toDouble(),
                        "EUR"
                    )
                )
            )

        assertEquals(expectedAmount, entities.first().transactionAmount.formattedAmount)
    }

    @Test
    fun valueIsNegative_AmountMappedWithMinusAndCurrency() {
        val expectedAmount = "-12.34 EUR"

        val entities =
            mapper.toTransactionsViewEntities(
                createTransactionsRaw(
                    amount = createAmount(
                        (-1234).toDouble(),
                        "EUR"
                    )
                )
            )

        assertEquals(expectedAmount, entities.first().transactionAmount.formattedAmount)
    }

    @Test
    fun valueIsPositive_AmountColorIsColorPrimary() {
        val expectedAmountColor = R.color.colorPrimary

        val entities =
            mapper.toTransactionsViewEntities(
                createTransactionsRaw(amount = createAmount(value = 1234.toDouble()))
            )

        assertEquals(expectedAmountColor, entities.first().transactionAmount.color)
    }

    @Test
    fun valueIsNegative_AmountColorIsRed() {
        val expectedAmountColor = R.color.red

        val entities =
            mapper.toTransactionsViewEntities(
                createTransactionsRaw(amount = createAmount(value = -(1234).toDouble()))
            )

        assertEquals(expectedAmountColor, entities.first().transactionAmount.color)
    }
}