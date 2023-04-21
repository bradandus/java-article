package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	
	private static int lastArticleId = 0;
	private static int lastMemberId = 0;
	public void Start() {
		System.out.println("== 게시만 만들기 시작");
		makeTestData();
		Scanner sc = new Scanner(System.in);

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
				
				
			} else if(command.equals("member join")) {
				int id = lastMemberId +1;
				lastMemberId = id;
				
				String loginId;
				String loginPw;
				String confirmPw;
				String regDate = Util.getRegDate();
				
				
				while(true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine();
					
					if(isJoinableLoginId(loginId) == false) {
						System.out.printf("%s는 이미 가입된 아이디입니다.", loginId);
						continue;
					}
					if(loginId.length() < 6) {
						System.out.println("아이디는 6글자 이상 입력해주세요.");
						continue;
					}else {
						break;
					}
					
				}
				while(true) {
					System.out.printf("비밀번호 : ");
					loginPw = sc.nextLine();
					if(loginPw.length() < 8) {
						System.out.println("비밀번호는 8글자 이상 입력해주세요.");
						continue;
					}
					System.out.printf("비밀번호 확인 : ");
					confirmPw = sc.nextLine();
					if(loginPw.equals(confirmPw)){
						break;
					}else {
						System.out.println("같은 비밀번호를 입력해주세요");
						continue;
					}
					
				}	
				System.out.printf("이름 : ");
				String userName = sc.nextLine();
					
				Member member = new Member(id, loginId, loginPw, regDate, userName);
				members.add(member);
				System.out.printf("%s 번째 회원이 가입되었습니다.%n", id);
				
			}	else if (command.startsWith("article list")) {
			
				System.out.println("게시물리스트 보기입니다.");
				if (articles.size() == 0) {
					System.out.println("등록된 게시물이 없습니다.");
					continue;
				} else {
					String searchKeyword = command.substring("article.list".length()).trim();
					System.out.println("검색어 : "+ searchKeyword);
					List<Article> forListArticle = articles; // 전체 리스트 복제
					if(searchKeyword.length() > 0) { // 검색어가 있다면;
						forListArticle = new ArrayList<>(); // 검색어가 있으면 한번 초기화
						for(Article article : articles) {
							if (article.title.contains(searchKeyword) ) {
								forListArticle.add(article);// 검색어가 있는거만 계속 애
							}
						}
						if (forListArticle.size() == 0) {
							System.out.println("일치하는 검색결과가 없습니다.");
							System.out.println("전체 리스트를 보여줍니다.");
							forListArticle = articles; // 다시 전체 덮어씌우기
						}
					}else {
						System.out.println("검색어가 없습니다. 전체 리스트를 보여줍니다.");
					}
					System.out.println("번호  |       제목       |        작성/수정일      | 조회수 ");
					for (int i = 0; i < forListArticle.size(); i++) {
						System.out.printf("%2d  | %10s | %s | %d%n", forListArticle.get(i).id, forListArticle.get(i).title, forListArticle.get(i).regDate, forListArticle.get(i).hit);
					}
				}
			}else if(command.equals("member list")) {
				System.out.println("멤버 리스트 보기 입니다.");
				if(members.size() == 0) {
					System.out.println("등록된 멤버가 없습니다.");
				}else {
					System.out.println("회원번호 |    아이디    |   비밀번호   |     이름     |     등록일");
					for (int i = 0 ; i < members.size(); i++) {
						System.out.printf("%4d   | %10s | %10s | %10s | %s%n", members.get(i).id, members.get(i).loginId, members.get(i).loginPw, members.get(i).userName, members.get(i).regDate );
					}
				}
			} else if (command.equals("article write")) {

				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getRegDate();
				Article article = new Article(id, title, body, regDate);
				articles.add(article);
				System.out.printf("%d번째 글이 생성되었습니다.%n", id);
				System.out.printf("제목 : %s%n", title);
				System.out.printf("내용 : %s%n", body);

			} else if (command.startsWith("article detail")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = getArticleById(id);
				if (foundArticle == null){
					System.out.printf("%d 번째 게시물을 찾을 수 없습니다.%n", id);
				}else {
					foundArticle.increasHit();
					System.out.printf("번호 : %d%n", foundArticle.id);
					System.out.printf("제목 : %s%n", foundArticle.title);
					System.out.printf("내용 : %s%n", foundArticle.body);
					System.out.printf("등록 시간 : %s%n", foundArticle.regDate);
					System.out.printf("조회수 : %d%n", foundArticle.hit);
				}

			} else if (command.startsWith("article delete")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				int foundIndex = getArticleIndexById(id);
				if (foundIndex == -1) {
					System.out.printf("%d 번 게시물을 찾을 수 없습니다.%n", id);
				}else {
					articles.remove(foundIndex);
					System.out.printf("%d 번 게시물을 삭제했습니다.%n", id);
				}
				
			} else if (command.startsWith("member delete")) {
				try{
					String[] commandBits = command.split(" ");
					String foundId = commandBits[2];
					System.out.println(foundId);
					boolean found = false;
					
					for (int i = 0; i < members.size(); i++) {
						if (foundId.equals(members.get(i).loginId)) {
								members.remove(i);
								found = true;
								System.out.printf("%s 멤버 삭제하였습니다.%n", foundId);
								break;
							}
						}
						if (found == false) {
							System.out.printf("%s 멤버를 찾을 수 없습니다.%n", foundId);
						}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("잘못된 명령어로 인한 오류입니다. member delete loginId 를 입혁해주세요");
				}
			} else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = getArticleById(id);
				if (foundArticle == null){
					System.out.printf("%d 번째 게시물을 찾을 수 없습니다.", id);
				}else {
					System.out.printf("%d 번 게시물을 찾았습니다.%n", id);
					System.out.printf("수정할 제목 : ");
					foundArticle.title = sc.nextLine();
					System.out.printf("수정할 내용 : ");
					foundArticle.body = sc.nextLine();
					foundArticle.regDate = Util.getRegDate();
					System.out.printf("%d 게시물이 수정 완료 되었습니다.%n", id);
					
				}
			} else if (command.startsWith("member modify")) {
				String[] commandBits = command.split(" ");
				String foundId = commandBits[2];
				Member foundMember = null;
				for(int i = 0 ; i < members.size(); i++) {
					if (foundId.equals(members.get(i).loginId)) {
						System.out.printf("%s 아이디의 멤버를 찾았습니다.%n", foundId);
						foundMember = members.get(i);
				
						String loginPw;
						String confirmPw;
					
						while(true) {
							System.out.printf("변경할 비밀번호 : ");
							loginPw = sc.nextLine();
							if(loginPw.length() < 8) {
								System.out.println("비밀번호는 8글자 이상 입력해주세요.");
								continue;
							}
							System.out.printf("비밀번호 확인 : ");
							confirmPw = sc.nextLine();
							if(loginPw.equals(confirmPw)){
							
								break;
							}else {
								System.out.println("같은 비밀번호를 입력해주세요");
								continue;
							}
							
						}

						foundMember.loginPw = loginPw;
						System.out.printf("%s 멤버의 정보를 수정 완료 되었습니다.%n", foundId);
					}
				
				}
			} else {
				System.out.println("잘못된 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("== 게시판 만들기 종료 ==");
		;
	}
	
	// 게시물 아이디로 찾기
	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		if (index != -1) { // 일치하는 index가 있는 경우 (-1은 찾지 못한 경우)
			return articles.get(index);
		}
		return null;
	}
	
	// 게시물 아이디로 인덱스 찾기
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	// 회원 로그인 아이디가 가입되어 있는 회원인지 찾기
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) { // 일치하는 index가 없는 경우
			return true;
		}
		return false;
	}
	// 회원 로그인 아이디로 인덱스 찾기
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		
		return -1;
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
			int id = lastMemberId + 1;
			lastMemberId = id;
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
