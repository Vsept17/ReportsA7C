package com.a7c.reportsa7c.TeknisiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a7c.reportsa7c.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputLaporan extends AppCompatActivity {

    Button submit;
    EditText namaTek;
    EditText namaCus;
    EditText alamatCus;
    EditText telpCus;
    EditText jenisJob;
    EditText jumlahPC;
    EditText tglJob;
    EditText totalFee;

    DatabaseReference databaseTeknisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_laporan);

        databaseTeknisi = FirebaseDatabase.getInstance().getReference("teknisi");

        namaTek = (EditText) findViewById(R.id.nama_teknisi);
        namaCus = (EditText) findViewById(R.id.nama_customer);
        alamatCus = (EditText) findViewById(R.id.alamat_cus);
        telpCus = (EditText) findViewById(R.id.telp_cus);
        jenisJob = (EditText) findViewById(R.id.jenis_job);
        jumlahPC = (EditText) findViewById(R.id.jumlah_pc);
        tglJob = (EditText) findViewById(R.id.tgl_kerja);
        totalFee = (EditText) findViewById(R.id.total_fee);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeknisi();
                namaTek.setText("");
                namaCus.setText("");
                alamatCus.setText("");
                telpCus.setText("");
                jenisJob.setText("");
                jumlahPC.setText("");
                tglJob.setText("");
                totalFee.setText("");

            }
        });
    }
    private void addTeknisi(){
        String namaTeknisi = namaTek.getText().toString().trim();
        String namaCust = namaCus.getText().toString().trim();
        String alamatCust = alamatCus.getText().toString().trim();
        String telpCust = telpCus.getText().toString().trim();
        String jenisJobs = jenisJob.getText().toString().trim();
        String jmlPC = jumlahPC.getText().toString().trim();
        String tglJobs = tglJob.getText().toString().trim();
        String ttlFee = totalFee.getText().toString().trim();


        if(!TextUtils.isEmpty(namaTeknisi)) {
           String id = databaseTeknisi.push().getKey();

            Teknisi teknisi = new Teknisi(namaTeknisi, namaCust, alamatCust, telpCust, jenisJobs, jmlPC, tglJobs,ttlFee);

            databaseTeknisi.child(namaTeknisi).setValue(teknisi);

            Toast.makeText(this, "Laporan Berhasil Di Input", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Masukkan data", Toast.LENGTH_LONG).show();
        }

    }
}
