import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aulia_vi.laundry.R
import com.aulia_vi.laundry.modeldata.ModelPelanggan

class DataPelangganAdapter(
    private val listPelanggan: ArrayList<ModelPelanggan>
) : RecyclerView.Adapter<DataPelangganAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPelanggan[position]
        holder.tvID.text = item.idPelanggan
        holder.tvNama.text = item.namaPelanggan
        holder.tvAlamat.text = item.alamatPelanggan
        holder.tvNoHP.text = item.noHPPelanggan
        holder.tvTerdaftar.text = item.terdaftar
        holder.cvCard.setOnClickListener{

        }
        holder.btHubungi.setOnClickListener{

        }
        holder.btLihat.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return listPelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val  cvCard: CardView = itemView.findViewById(R.id.cvCARD_PELANGGAN)
        val  tvID: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_Id)
        val  tvNama: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_Nama)
        val  tvAlamat: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_Alamat)
        val  tvNoHP: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_NoHp)
        val  tvTerdaftar: TextView = itemView.findViewById(R.id.tvCARD_PELANGGAN_Terdaftar)
        val  btHubungi: Button = itemView.findViewById(R.id.btCARD_PELANGGAN_Hubungi)
        val  btLihat: Button = itemView.findViewById(R.id.btCARD_PELANGGAN_Lihat)
    }
}
