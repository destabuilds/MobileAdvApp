package com.example.mobileadvapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileadvapp.databinding.FragmentCartBinding
import java.text.NumberFormat
import java.util.Locale

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val args: CartFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.productName
        val qty = args.quantity
        val pricePerUnit = 12000000
        val totalPrice = qty * pricePerUnit

        val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

        binding.tvCartDetail.text = "Nama Produk: $name\n" +
                "Jumlah: $qty unit\n" +
                "Harga per Unit: ${formatter.format(pricePerUnit)}\n\n" +
                "Total Estimasi Harga: ${formatter.format(totalPrice)}"

        // PopUpTo Checkout back to CategoryFragment
        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToCategoryFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}