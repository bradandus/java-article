package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.controller.ArticleController;
import article.controller.MemberController;
import article.dto.Article;
import article.dto.Member;
import article.util.Util;

public class App{
	private static List<Article> articles;
	private static List<Member> members;
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	
	
	public static int lastArticleId = 0;


	public void Start() {
		System.out.println("== 게시만 만들기 시작");
	
		makeTestData(); 
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);
		
		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine();
			command = command.trim(); // 입력받은 값의 양쪽 끝 공백 무시 id/pw 처리시에 필요
			if (command.length() == 0) { // 입력어가 없는 경우 명령어 입력 다시 받음
				continue; // while 문 처음으로 이동
			}
			if (command.equals("exit")) { // command 텍스트가 exit와 같다면 true 리턴,
				System.out.println("== 프로그램 종료 ==");
				break; // while 문 종료
				
				

				
			}	else if (command.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("등록된 게시물이 없습니다.");
					continue;
				} else {
					articleController.doList(command);
				}
	
			} else if (command.equals("article write")) {
				articleController.doWrite();

			} else if (command.startsWith("article detail")) {
				
				articleController.doDetail(command);


			} else if (command.startsWith("article delete")) {
				articleController.doDelete(command);
				
				
			} else if (command.startsWith("article modify")) {
				articleController.doModify(command);
				
			} else if(command.equals("member join")) {
				memberController.doJoin();
				
			} else if(command.equals("member list")) {
				memberController.doList();
								
			} else if (command.startsWith("member delete")) {
				memberController.doDelete(command);
				
			} else if (command.startsWith("member modify")) {
				memberController.doModify(command);
			} else {
				System.out.println("잘못된 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("== 게시판 만들기 종료 ==");
		;
	}
	

	private void makeTestData() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < 3 ; i++ ) {
			int id = lastArticleId + 1;
			lastArticleId = id;
			String title = "testTitle No."+id;
			String body = "testBody No."+id;
			String regDate = Util.getRegDate();
			Article article = new Article(id, title, body, regDate);
			articles.add(article);
			System.out.printf("%d번째 글이 생성되었습니다.%n", id);
			}
		for (int i = 0 ; i < 3 ; i++) {
			int id = i +1;
			MemberController.lastMemberId = id;
			String loginId = "member00"+id;
			String loginPw = "u1234567";
			String userName = "name00"+id;
			String regDate = Util.getRegDate();
			Member member = new Member(id, loginId, loginPw, regDate, userName);
			members.add(member);
			System.out.printf("%d번째 회원이 생성되었습니다.%n", id);
		}
	}


}
