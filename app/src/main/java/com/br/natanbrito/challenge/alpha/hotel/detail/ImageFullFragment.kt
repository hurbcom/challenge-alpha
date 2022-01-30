package com.br.natanbrito.challenge.alpha.hotel.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.br.natanbrito.challenge.alpha.databinding.FragmentImageFullBinding

class ImageFullFragment : Fragment() {

    private lateinit var hotelImage: String
    private lateinit var hotelDescription: String
    private lateinit var binding: FragmentImageFullBinding
    private lateinit var appCompatActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appCompatActivity = activity as AppCompatActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageFullBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            hotelImage = ImageFullFragmentArgs.fromBundle(it).image
            hotelDescription = ImageFullFragmentArgs.fromBundle(it).description
            setupLayout()
        }

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setupLayout() {
        with(binding) {
            val imageRequest = ImageRequest.Builder(requireContext())
                .data(hotelImage)
                .target { drawable ->
                    val bitmap = drawable.toBitmap()
                    image.load(bitmap)
                }
                .build()

            ImageLoader(requireContext()).enqueue(imageRequest)

            closeButton.setOnClickListener {
                appCompatActivity.onBackPressed()
            }

            description.text = hotelDescription
        }
    }
}
