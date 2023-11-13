package Project.Api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PublicApi {
    public static void main(String[] args) {
        String csvFilePath = "/Users/moon/Projects/TravelBusan/src/main/java/Project/Api/BusanSights.csv";

        try {
            // JDBC 연결
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://database-1.cqcdgnfc7io0.ap-southeast-2.rds.amazonaws.com:3306/travelbusan?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true",
                    "admin",
                    "12312312"
            );

            // CSV 파일 읽기
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                // 헤더 스킵
                reader.readLine();

                // 각 라인 처리
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                    // PreparedStatement를 사용하여 데이터베이스에 데이터 삽입
                    String sql = "INSERT INTO SIGHTS (sights_id,name, info, addr, mapx, mapy, img, city, homepage, number, traffic_report, title, open_date  ) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, data[0].replaceAll("\"", ""));  // 고유 아이디값
                        statement.setString(2, data[1].replaceAll("\"", ""));  // 콘텐츠명 (명소명)
                        statement.setString(3, data[19].replaceAll("\"", "")); // 내용
                        statement.setString(4, data[8].replaceAll("\"", ""));  // 주소
                        statement.setDouble(5, Double.parseDouble(data[4])); // 경도
                        statement.setDouble(6, Double.parseDouble(data[3])); // 위도
                        statement.setString(7, data[17].replaceAll("\"", "")); // 이미지
                        statement.setString(8, data[2].replaceAll("\"", ""));  // 구군
                        statement.setString(9, data[10].replaceAll("\"", ""));  // 홈페이지
                        statement.setString(10, data[9].replaceAll("\"", ""));  // 연락처
                        statement.setString(11, data[11].replaceAll("\"", ""));  // 교통정보
                        statement.setString(12, data[6].replaceAll("\"", ""));  // 교통정보
                        statement.setString(13, data[14].replaceAll("\"", ""));  // 교통정보



                        // 콘텐츠ID(0),콘텐츠명(1),구군(2),위도(3),경도(4),여행지(5),제목(6),부제목(7),주소(8),연락처(9),홈페이지(10),
                        // 교통정보(11),운영일(12),휴무일(13),운영 및 시간(14),이용요금(15),편의시설(16),이미지URL(17),썸네일이미지URL(18),상세내용(19)



                        // 실행
                        statement.executeUpdate();
                    }
                }

                System.out.println("DB 저장 완료");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

            // 연결 닫기
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
