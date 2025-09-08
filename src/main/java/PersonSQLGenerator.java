import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class PersonSQLGenerator {
    static String[] firstNames = {
            "أحمد", "محمد", "علي", "حسن", "يوسف", "كريم", "رامي", "خالد", "نور", "فؤاد",
            "فاطمة", "زينب", "رنا", "ليلى", "مريم", "هبة", "دلال", "نسرين", "علا", "عبير"
    };

    static String[] familyNames = {
            "الخير", "العبدالله", "حمدان", "الزعتر", "خليفة", "السيد", "القادري", "الموسوي", "شهاب", "بزي",
            "المراد", "شحادة", "عيتاني", "سلام", "الصمد", "حمود", "برجاوي", "الأشقر", "الدبس", "الطفيلي"
    };

    static String[] addresses = {
            "بيروت", "طرابلس", "صيدا", "صور", "بعلبك", "زحلة", "جبيل", "عكار", "النبطية", "صوفر",
            "برج البراجنة", "الشويفات", "عاليه", "حلبا", "الدامور", "الضاحية", "كسروان", "دير الأحمر", "دوحة", "حمانا"
    };

    static String[] genders = {"ذكر", "أنثى"};

    static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("insert_person.sql");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 1; i <= 10000; i++) {
            String fullName = getRandomFullName();
            String address = addresses[rand.nextInt(addresses.length)];
            String gender = genders[rand.nextInt(genders.length)];
            String birthday = randomDate();
            String phone = "03" + (rand.nextInt(900000) + 100000);

            String sql = String.format(
                    "INSERT INTO person (id, name, address, gender, birthday_date, phone) " +
                            "VALUES (%d, '%s', '%s', '%s', '%s', '%s');\n",
                    i, fullName, address, gender, birthday, phone
            );

            writer.write(sql);
        }

        writer.close();
        System.out.println("تم إنشاء الملف: insert_person.sql");
    }

    public static String getRandomFullName() {
        String firstName = firstNames[rand.nextInt(firstNames.length)];
        String familyName = familyNames[rand.nextInt(familyNames.length)];
        return firstName + " " + familyName;
    }

    public static String randomDate() {
        int year = rand.nextInt(2005 - 1970 + 1) + 1970;
        int month = rand.nextInt(12) + 1;
        int day = rand.nextInt(28) + 1;
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
