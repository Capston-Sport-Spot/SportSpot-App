package com.example.sportspot.view.article

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportspot.R
import com.example.sportspot.databinding.FragmentArticleBinding
import com.example.sportspot.response.ArticleResponseItem
import com.example.sportspot.view.adapter.ArticleAdapter

class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private lateinit var rvArticle: RecyclerView
    private var list = ArrayList<ArticleResponseItem>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvArticle = view.findViewById(R.id.rv_article)
        rvArticle.setHasFixedSize(true)

//        list.addAll(getListArticle())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvArticle.layoutManager = LinearLayoutManager(requireActivity())
        val listTipsAdapter = ArticleAdapter(list)
        rvArticle.adapter = listTipsAdapter
        listTipsAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ArticleResponseItem) {
                val intentDetail = Intent(requireActivity(), DetailArticleActivity::class.java)
//                intentDetail.putExtra("DATA",data)
                startActivity(intentDetail)
            }
        })
    }

//    private fun getListArticle(): ArrayList<ArticleResponseItem> {
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataDescription = resources.getStringArray(R.array.data_description)
//        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//        val listTips = ArrayList<ArticleResponseItem>()
//        for (i in dataName.indices) {
//            val food = ArticleResponseItem(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
//            listTips.add(food)
//        }
//        return listTips
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}