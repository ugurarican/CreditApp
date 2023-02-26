package com.ugurarican.creditapp.dataAccess;

import com.ugurarican.creditapp.entities.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditApplication,String> {

    //KrediSkoruRepository ile bizim yazdığımız bilgilerin bir veritabana kaydedilmesi sağlanacaktır. Bu işlemler için @Repository anatasyonunun
    //tanımlanması gerekmektedir.


}
