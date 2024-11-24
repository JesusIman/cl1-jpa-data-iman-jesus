package pe.edu.I202332153.cl1_jpa_data_iman_jesus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.I202332153.cl1_jpa_data_iman_jesus.entity.Country;
import pe.edu.I202332153.cl1_jpa_data_iman_jesus.repository.CountryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class Cl1JpaDataImanJesusApplication implements CommandLineRunner {

	@Autowired
	CountryRepository countryRepository;

	public static void main(String[] args) {

		SpringApplication.run(Cl1JpaDataImanJesusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/**
		 * ifPresentOrElse
		 */

		// encontrar el país "ARG" (Argentina)
		Optional<Country> optionalCountry = countryRepository.findById("ARG");

		optionalCountry.ifPresentOrElse(
				country -> {
					// si se encuentra "ARG", imprimir los lenguajes
					System.out.println("Lenguajes en ARG:");
					country.getCountryLanguage().forEach(language -> System.out.println(language.getLanguage()));
				},
				() -> {
					// Si no se encuentra "ARG", intentar con "PER" (Perú)
					Optional<Country> optionalPeru = countryRepository.findById("PER");
					optionalPeru.ifPresentOrElse(
							peru -> {
								// si se encuentra "PER", imprimir los lenguajes
								System.out.println("Lenguajes en PER:");
								peru.getCountryLanguage().forEach(language -> System.out.println(language.getLanguage()));
							},
							() -> {
								// si no se encuentra ni "ARG" ni "PER"
								System.out.println("No se encontró el país 'PER' ni 'ARG'.");
							}
					);
				}
		);



//		List<String> lista = List.of("COL","ARG");
//		lista.stream().filter((id) -> {
//			return countryRepository.existsById(id);
//		}).collect(Collectors.toList());
//		lista.stream().filter(countryRepository::existsById).collect(Collectors.toList());
//		countryRepository.deleteAllById(lista);

	}

}
