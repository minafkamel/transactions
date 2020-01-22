package com.minafkamel.transactions.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.minafkamel.transactions.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionsAdapter(
    private val entities: List<TransactionViewEntity>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount() = entities.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TransactionViewHolder).onBind(entities[position])
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(entity: TransactionViewEntity) {

            itemView.amountTextView.text = entity.transactionAmount.formattedAmount
            itemView.amountTextView.setTextColor(
                ContextCompat.getColor(
                    itemView.context, entity.transactionAmount.color
                )
            )
            itemView.transactionDateTextView.text = entity.title
            itemView.transactionDateTextView.text = entity.date
            itemView.categoryTextView.text = entity.category

            if (entity.image.shouldShowDefaultImage) {
                itemView.transactionImageView.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_transactions_default))
            } else {
                Picasso.get().isLoggingEnabled = true
                Picasso
                    .get()
                    .load(entity.image.imageUrl)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.ic_transactions_default)
                    .fit()
                    .centerCrop()
                    .into(itemView.transactionImageView)
            }
        }

    }
}