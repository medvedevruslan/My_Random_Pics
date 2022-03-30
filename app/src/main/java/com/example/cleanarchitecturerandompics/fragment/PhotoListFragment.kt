package com.example.cleanarchitecturerandompics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.models.Photo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecturerandompics.databinding.PhotoFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Display a list of [Photo]s.
 */
class PhotoListFragment : Fragment() {

    private val photoListViewModel: PhotoListViewModel by viewModel()

    private lateinit var photoFragmentBinding: PhotoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        photoFragmentBinding = PhotoFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = photoListViewModel
        }
        photoFragmentBinding.lifecycleOwner = this.viewLifecycleOwner
        return photoFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photoFragmentBinding.photoList.layoutManager = LinearLayoutManager(this@PhotoListFragment.context)
        photoFragmentBinding.photoList.adapter = PhotoAdapter()
        photoListViewModel.requestItems()
    }
}