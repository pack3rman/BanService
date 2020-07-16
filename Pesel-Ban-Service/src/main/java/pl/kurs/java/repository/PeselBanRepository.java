package pl.kurs.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.java.model.BanedPesel;

public interface PeselBanRepository extends JpaRepository<BanedPesel,Long> {

}
