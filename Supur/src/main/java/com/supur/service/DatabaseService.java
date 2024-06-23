package com.supur.service;

import com.supur.entity.*;
import com.supur.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Kullanıcıları oluştur
            User user1 = new User();
            user1.setUsername("ali_kaya");
            user1.setPassword("ali12345");
            user1.setPhoneNumber("05555555555");
            user1.setEmail("ali.kaya@example.com");
            user1.setBio("Merhaba, ben Ali. 10 yıllık tamirciyim.");

            User user2 = new User();
            user2.setUsername("ayse_erdem");
            user2.setPassword("ayse12345");
            user2.setPhoneNumber("05555555555");
            user2.setEmail("ayse.erdem@example.com");
            user2.setBio("Merhaba, ben Ayşe. Bahçe işleri uzmanıyım.");

            User user3 = new User();
            user3.setUsername("mehmet_duman");
            user3.setPassword("mehmet12345");
            user3.setPhoneNumber("05555555555");
            user3.setEmail("mehmet.duman@example.com");
            user3.setBio("Merhaba, ben Mehmet. Profesyonel yazılımcıyım.");

            User user4 = new User();
            user4.setUsername("zeynep_turan");
            user4.setPassword("zeynep12345");
            user4.setPhoneNumber("05555555555");
            user4.setEmail("zeynep.turan@example.com");
            user4.setBio("Merhaba, ben Zeynep. Temizlik hizmetleri sunuyorum.");

            User user5 = new User();
            user5.setUsername("burak_tas");
            user5.setPassword("burak12345");
            user5.setPhoneNumber("05555555555");
            user5.setEmail("burak.tas@example.com");
            user5.setBio("Merhaba, ben Burak. Her türlü tamir işini yaparım.");

            userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

            // İş ilanlarını oluştur
            Job job1 = new Job();
            job1.setTitle("Kombi Tamiri");
            job1.setDescription("Evde kombi tamiri yapılacaktır.");
            job1.setCategory(Category.TAMIR);
            job1.setUser(user1);

            Job job2 = new Job();
            job2.setTitle("Bahçe Temizliği");
            job2.setDescription("Bahçenin temizlenmesi gerekiyor.");
            job2.setCategory(Category.TEMIZLIK);
            job2.setUser(user2);

            Job job3 = new Job();
            job3.setTitle("Web Yazılım");
            job3.setDescription("Web sitesi için yazılım yapılacak.");
            job3.setCategory(Category.YAZILIM);
            job3.setUser(user3);

            Job job4 = new Job();
            job4.setTitle("Ev Temizliği");
            job4.setDescription("Detaylı ev temizliği yapılacaktır.");
            job4.setCategory(Category.TEMIZLIK);
            job4.setUser(user4);

            Job job5 = new Job();
            job5.setTitle("Mobilya Montajı");
            job5.setDescription("Yeni alınan mobilyaların montajı yapılacaktır.");
            job5.setCategory(Category.TAMIR);
            job5.setUser(user5);

            Job job6 = new Job();
            job6.setTitle("Bahçe Peyzaj");
            job6.setDescription("Bahçeye peyzaj düzenlemesi yapılacaktır.");
            job6.setCategory(Category.DIGER);
            job6.setUser(user2);

            Job job7 = new Job();
            job7.setTitle("Mobile App Development");
            job7.setDescription("Mobil uygulama geliştirme hizmeti sunulacaktır.");
            job7.setCategory(Category.YAZILIM);
            job7.setUser(user3);

            Job job8 = new Job();
            job8.setTitle("Klozet Tamiri");
            job8.setDescription("Klozet tamiri yapılacaktır.");
            job8.setCategory(Category.TAMIR);
            job8.setUser(user1);

            Job job9 = new Job();
            job9.setTitle("Halı Yıkama");
            job9.setDescription("Evde halı yıkama hizmeti sunulacaktır.");
            job9.setCategory(Category.TEMIZLIK);
            job9.setUser(user4);

            Job job10 = new Job();
            job10.setTitle("Veri Analizi");
            job10.setDescription("Büyük veri analizi hizmeti sunulacaktır.");
            job10.setCategory(Category.YAZILIM);
            job10.setUser(user3);

            jobRepository.saveAll(Arrays.asList(job1, job2, job3, job4, job5, job6, job7, job8, job9, job10));
        }
    }
}
