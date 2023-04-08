package article;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 게시만 판들기 시작");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		
		while(true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine();
			command = command.trim(); // 입력받은 값의 공백 무시
			if(command.length() == 0) {
				continue; //while 문 처음으로 이동
			}
			if(command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}else if(command.equals("article list")) {
				System.out.println("게시물리스트 보기입니다.");
			}else if(command.equals("article write")) {
				int id = lastArticleId +1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번째 글이 생성되었습니다.%n", id);
				System.out.printf("제목 : %s%n", title);
				System.out.printf("내용 : %s%n", body);
				
			}else {
				System.out.println("잘못된 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("== 게시판 만들기 종료 ==");;
	}

}
