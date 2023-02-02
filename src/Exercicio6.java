import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Para gestão dos períodos de vacinação, você é responsável por criar
 * um programa que receba a data de vacina e imprima as próximas
 * 3 doses baseado nas seguintes regras;
 *
 *     As doses devem ser ministradas de 3 em 3 meses
 *     Como os postos de vacinação só abrem em dias da semana se a data da
 *     vacinação for sábado, deve ser antecipada para sexta, caso dê num
 *     domingo deve ser ministrada na segunda. (Resposta: via github, cole o link do projeto)
 * */
public class Exercicio6 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Informe a de vascinação no formato (dd/MM/yyyy): ");
        String dataVascinacaoTexto = scanner.nextLine();

        try {
            LocalDate dataVascinacao = LocalDate.parse(dataVascinacaoTexto, formatador);

            LocalDate dataDose2 = validadorDiaSemanaVascina(dataVascinacao);
            LocalDate dataDose3 = validadorDiaSemanaVascina(dataDose2);
            LocalDate dataDose4 = validadorDiaSemanaVascina(dataDose3);

            System.out.println("Dose 1: " + dataVascinacao);
            System.out.println("Dose 2: " + dataDose2);
            System.out.println("Dose 3: " + dataDose3);
            System.out.println("Dose 4: " + dataDose4);

        }catch (DateTimeException e){
            System.out.println("Formato de data inválida.");
        }

    }

    public static LocalDate validadorDiaSemanaVascina (LocalDate dataUltimaVascina){
        LocalDate dataProximaVascina = dataUltimaVascina.plus(3, ChronoUnit.MONTHS);
        if(dataProximaVascina.getDayOfWeek() == DayOfWeek.SATURDAY){
            dataProximaVascina = dataProximaVascina.minus(1, ChronoUnit.DAYS);
        }else if(dataProximaVascina.getDayOfWeek() == DayOfWeek.MONDAY){
            dataProximaVascina = dataProximaVascina.plus(1, ChronoUnit.DAYS);
        }
        return dataProximaVascina;
    }

}
