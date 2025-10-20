package datetime_demo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeExample {
    public static void main(String[] args) {

        // Lấy thời gian hiện tại
        LocalDate today = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("Ngày hôm nay: " + today);
        System.out.println("Giờ hiện tại: " + timeNow);
        System.out.println("Ngày & giờ hiện tại: " + currentDateTime);

        // Tạo ngày/tháng/năm cụ thể
        LocalDate birthday = LocalDate.of(2003, Month.JUNE, 15);
        System.out.println("Sinh nhật: " + birthday);

        // Cộng / Trừ ngày tháng
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);
        System.out.println("Tuần sau: " + nextWeek);
        System.out.println("Tháng trước: " + lastMonth);

        // Tính số ngày giữa 2 mốc
        long daysBetween = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("Số ngày từ sinh nhật tới nay: " + daysBetween);

        // Định dạng ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatted = currentDateTime.format(formatter);
        System.out.println("Định dạng ngày giờ: " + formatted);

        // Phân tích chuỗi ngày giờ thành đối tượng
        String input = "20/10/2025 10:00:00";
        LocalDateTime parsedDateTime = LocalDateTime.parse(input, formatter);
        System.out.println("Parse từ chuỗi: " + parsedDateTime);

        // ZonedDateTime (khu vực múi giờ)
        ZonedDateTime vnTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        ZonedDateTime usTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("\nGiờ Việt Nam: " + vnTime);
        System.out.println("Giờ New York: " + usTime);
    }
}
