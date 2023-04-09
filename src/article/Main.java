package article;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 게시만 판들기 시작");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		int num = 0;
		ArrayList <Article> articles = new ArrayList<Article>();
		
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
				if(articles.size() == 0){
					System.out.println("등록된 게시물이 없습니다.");
				}else {
					System.out.println("번호 | 제목");
					for(int i =0 ; i < articles.size(); i++) {
						System.out.printf("%d  | %s |%n", articles.get(i).id, articles.get(i).title);

					}
				}
			}else if(command.equals("article write")) {
				
				int id = lastArticleId +1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				Article article = new Article(id, title, body);
				articles.add(article);
								
				System.out.printf("%d번째 글이 생성되었습니다.%n", id);
				System.out.printf("제목 : %s%n", title);
				System.out.printf("내용 : %s%n", body);
			}else if(command.startsWith("article detail")) {
				String[] commandBits = command.split(" ");
				int detailId = Integer.parseInt(commandBits[2]);
				boolean found = false;
				for(int i = 0 ; i < articles.size(); i++) {
					if (detailId == articles.get(i).id) {
						found = true;
					}
				}
				if(found == false) {
					System.out.println("게시물이 존재하지 않습니다.");
				}else {
					System.out.printf("%d 번 게시물이 존재합니다.", detailId);
					System.out.printf("번호 : %d%n", articles.get(detailId).id);
					System.out.printf("제목 : %d%n", articles.get(detailId).title);
					System.out.printf("내 : %d%n", articles.get(detailId).body);
				}
				

				
				//				try {  //게시물이 오류를 바환할
//					System.out.printf("ID : %s%n", articles.get(a).id);
//					System.out.printf("제목 : %s%n", articles.get(a).title);
//					System.out.printf("내 : %s%n", articles.get(a).id);
//						
//				} catch(IndexOutOfBoundsException e) {
//					System.out.printf("게시물을 찾을 수 없습니다.%n");
//				}
			}else {
				System.out.println("잘못된 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("== 게시판 만들기 종료 ==");;
	}

}

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

}
