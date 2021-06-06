package com.example.loginextractdetail.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginextractdetail.GlideApp
import com.example.loginextractdetail.R
import com.example.loginextractdetail.data.model.userextract.Installment
import com.example.loginextractdetail.databinding.ItemBoughtBinding
import com.example.loginextractdetail.extensions.load
import com.example.loginextractdetail.utils.ClickContract

class HomeAdapter(
        private var installmentList: List<Installment>,
        private val listener: ClickContract
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBoughtBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBoughtBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            with(installmentList[position]) {

                holder.itemView.setOnClickListener {
                    listener.onClick(this)
                }

                binding.tvItemTitle.text = detail.store ?: "Loja Indisponivel"
                binding.tvItemDescription.text = detail.name
                        ?: "Descrição do item temporariamente indisponivel"
                binding.tvParcela.text = installment

                binding.tvParcelaValue.text = detail.total_value

                var url1 = detail.images?.firstOrNull()
                url1 = url1?.substring(0, 4) + "s" + url1?.substring(4, url1.length)

                var url2 = detail.images?.lastOrNull()

                url2 = url2?.substring(0, 4) + "s" + url2?.substring(4, url2.length)

                binding.ivItem.load(url1)

                if (url2 != "https://icoded.com.br/api/images/" && url1 == "https://icoded.com.br/api/images/") {
                    binding.ivItem.load(url2)
                } else if (url1 == "https://icoded.com.br/api/images/" && url2 == "https://icoded.com.br/api/images/") {

                    GlideApp.with(itemView.context).load(R.drawable.ic_launcher_background).into(binding.ivItem)
                }
            }
        }
    }

    override fun getItemCount() = installmentList.size

}