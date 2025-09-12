import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wegyanik.ProductAdapter
import com.example.wegyanik.ProductRepository
import com.example.wegyanik.R
import kotlinx.coroutines.launch

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        productAdapter = ProductAdapter { url ->
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
                startActivity(intent)
            }
        }
        recyclerView.adapter = productAdapter

        fetchProductData()
    }

    private fun fetchProductData() {
        viewLifecycleOwner.lifecycleScope.launch {

            val cached = ProductRepository.getCachedProducts()
            if (cached != null && cached.isNotEmpty()) {
                productAdapter.submitList(cached)
                Log.d("ProductFragment", "Loaded products from cache")
                return@launch
            }


            try {
                val response = RetrofitInstance.api.getProducts()
                if (response.isSuccessful && response.body() != null) {
                    val products = response.body()!!.data


                    ProductRepository.saveProducts(products)


                    productAdapter.submitList(products)
                    Log.d("ProductFragment", "Fetched products from API")
                } else {
                    Log.e("ProductFragment", "API call failed with code ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProductFragment", "Exception: ${e.message}")
            }
        }
    }

}
