package com.hololo.tutorial.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class MainActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("Otomatik Veri").setContent("GM Müzik Çalar şarkıcı/albüm resimlerini, tarzlarını otomatik olarak bulur ve gösterir*. \n\n\n(*)Bu özelliğin doğru çalışabilmesi için şarkı bilgilerinin düzgün olması gereklidir").setBackgroundColor(Color.parseColor("#FF0957")).setDrawable(R.drawable.ss_1).setSummary("Devam ederek nasıl yapacağınızı öğrenin").build());

        addFragment(new Step.Builder().setTitle("Şarkıyı Seç").setContent("Şarkılarınızın listelendiği ana sekmeye geçin her şarkının yanındaki (⋮) dokunun ve açılan menüden Veri Düzenle'yi seçin").setBackgroundColor(Color.parseColor("#00D4BA")).setDrawable(R.drawable.ss_2).setSummary("Devam ederek nasıl güncelleyeceğinizi öğrenin").build());

        addFragment(new Step.Builder().setTitle("Verileri Düzenle").setContent("Açılan pencerede Şarkı/Şarkıcı Adı, Albüm ve Tarz seçenekleri kolayca güncellenebilir").setBackgroundColor(Color.parseColor("#1098FE")).setDrawable(R.drawable.ss_3).setSummary("Devam ederek işlem sonucunu öğrenin").build());

        addFragment(new Step.Builder().setTitle("Sonuç Harika!").setContent("Yaptığınız güncelleme sonrası albüm kapağı, sanatçının wiki bilgileri ve tarzlar ekranları harika görünüyor, bu işlemi diğer şarkılarınız için de yapmayı unutmayın").setBackgroundColor(Color.parseColor("#CA70F3")).setDrawable(R.drawable.ss_4).setSummary("Teşekkürler, iyi dinlemeler").build());
    }
}
