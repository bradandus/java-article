package article;

public class Main {
//	static ArrayList<Article> articles = new ArrayList<Article>();
//	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		new App().Start();
	}
}
//		System.out.println("== 게시만 만들기 시작");
//		makeTestData();
//
//		while (true) {
//			System.out.print("명령어 : ");
//			String command = sc.nextLine();
//			command = command.trim(); // 입력받은 값의 공백 무시
//			if (command.length() == 0) {
//				continue; // while 문 처음으로 이동
//			}
//			if (command.equals("exit")) {
//				System.out.println("== 프로그램 종료 ==");
//				break;
//			} else if (command.equals("article list")) {
//				System.out.println("게시물리스트 보기입니다.");
//				if (articles.size() == 0) {
//					System.out.println("등록된 게시물이 없습니다.");
//				} else {
//					System.out.println("번호 | 제목         | 작성/수정일           | 조회수 ");
//					for (int i = 0; i < articles.size(); i++) {
//						System.out.printf("%2d  | %10s | %s | %d%n", articles.get(i).id, articles.get(i).title,
//								articles.get(i).regDate, articles.get(i).hit);
//
//					}
//				}
//			} else if (command.equals("article write")) {
//
//				int id = articles.size() + 1;
//				System.out.printf("제목 : ");
//				String title = sc.nextLine();
//				System.out.printf("내용 : ");
//				String body = sc.nextLine();
//				String regDate = Util.getRegDate();
//				Article article = new Article(id, title, body, regDate);
//				articles.add(article);
//				System.out.printf("%d번째 글이 생성되었습니다.%n", id);
//				System.out.printf("제목 : %s%n", title);
//				System.out.printf("내용 : %s%n", body);
//
//			} else if (command.startsWith("article detail")) {
//				String[] commandBits = command.split(" ");
//				int id = Integer.parseInt(commandBits[2]);
//				Article foundArticle = getArticleById(id);
//				if (foundArticle == null){
//					System.out.printf("%d 번째 게시물을 찾을 수 없습니다.", id);
//				}else {
//					foundArticle.increasHit();
//					System.out.printf("번호 : %d%n", foundArticle.id);
//					System.out.printf("제목 : %s%n", foundArticle.title);
//					System.out.printf("내용 : %s%n", foundArticle.body);
//					System.out.printf("등록 시간 : %s%n", foundArticle.regDate);
//					System.out.printf("조회수 : %d%n", foundArticle.hit);
//				}
//					
////				try {
////					String[] commandBits = command.split(" ");
////					int id = Integer.parseInt(commandBits[2]);
////					boolean found = false;
////					for (int i = 0; i < articles.size(); i++) {
////						if (id == articles.get(i).id) {
////							found = true;
////							Article foundArticle = articles.get(i);
////							System.out.printf("번호 : %d%n", foundArticle.id);
////							System.out.printf("제목 : %s%n", foundArticle.title);
////							System.out.printf("내용 : %s%n", foundArticle.body);
////							System.out.printf("등록 시간 : %s%n", foundArticle.regDate);
////							articles.get(i).increasHit();
////							System.out.printf("조회수 : %d%n", foundArticle.hit);
////							break;
////						}
////
////					}
////					if (found == false) {
////						System.out.printf("%d 번 게시물을 찾을 수 없습니다.%n", detailId);
////
////					}
////				} catch (ArrayIndexOutOfBoundsException e) {
////					System.out.println("잘못된 명령어로 인한 오류입니다. article detail [NO] 를 입혁해주세요");
////				}
//
//			} else if (command.startsWith("article delete")) {
//				try {
//					String[] commandBits = command.split(" ");
//					int id = Integer.parseInt(commandBits[2]);
//					boolean found = false;
//					for (int i = 0; i < articles.size(); i++) {
//						if (id == articles.get(i).id) {
//							articles.remove(i);
//							found = true;
//							System.out.printf("%d 번 게시물을 삭제하였습니다.%n", id);
//							break;
//						}
//					}
//					if (found == false) {
//						System.out.printf("%d 번 게시물을 찾을 수 없습니다.%n", id);
//					}
//				} catch (ArrayIndexOutOfBoundsException e) {
//					System.out.println("잘못된 명령어로 인한 오류입니다. article delete [NO] 를 입혁해주세요");
//				}
//			} else if (command.startsWith("article modify")) {
//				String[] commandBits = command.split(" ");
//				int id = Integer.parseInt(commandBits[2]);
//				Article foundArticle = getArticleById(id);
//				if (foundArticle == null){
//					System.out.printf("%d 번째 게시물을 찾을 수 없습니다.", id);
//				}else {
//					System.out.printf("%d 번 게시물을 찾았습니다.%n", id);
//					System.out.printf("수정할 제목 : ");
//					foundArticle.title = sc.nextLine();
//					System.out.printf("수정할 내용 : ");
//					foundArticle.body = sc.nextLine();
//					foundArticle.regDate = Util.getRegDate();
//					System.out.printf("%d 게시물이 수정 완료 되었습니다.%n", id);
//					
//				}
////				try {
////					String[] commandBits = command.split(" ");
////					int id = Integer.parseInt(commandBits[2]);
////					
////					boolean found = false;
////					for (int i = 0; i < articles.size(); i++) {
////						if (id == articles.get(i).id) {
////							Article modifyArticle = articles.get(i);
////							System.out.printf("%d 번 게시물을 찾았습니다.%n", id);
////							System.out.printf("수정할 제목 : ");
////							modifyArticle.title = sc.nextLine();
////							System.out.printf("수정할 내용 : ");
////							modifyArticle.body = sc.nextLine();
////							modifyArticle.regDate = Util.getRegDate();
////							articles.set(i, modifyArticle);
////							System.out.printf("%d 게시물이 수정 완료 되었습니다.%n", id);
////							found = true;
////							break;
////						}
////					}
////					if (found == false) {
////						System.out.printf("%d 번 게시물을 찾을 수 없습니다.%n", id);
////					}
////				} catch (ArrayIndexOutOfBoundsException e) {
////					System.out.println("잘못된 명령어로 인한 오류입니다. article modify [NO] 를 입혁해주세요");
////				}
//			} else {
//				System.out.println("잘못된 명령어입니다.");
//			}
//		}
//		sc.close();
//		System.out.println("== 게시판 만들기 종료 ==");
//		;
//	}
//
//	private static void makeTestData() {
//		// TODO Auto-generated method stub
//		System.out.println("테스트 데이터 생성");
//		System.out.printf("생성할 게시물 갯수를 입력하세요 : ");
//		int testCount = sc.nextInt();
//		for (int i = 0 ; i < testCount ; i++ ) {
//			int id = articles.size() + 1;
//			String title = "testTitle No."+id;
//			String body = "testBody No."+id;
//			String regDate = Util.getRegDate();
//			Article article = new Article(id, title, body, regDate);
//			articles.add(article);
//			System.out.printf("%d번째 글이 생성되었습니다.%n", id);
//			}
//		}
//	private static Article getArticleById(int id) {
//		for(int i = 0; i < articles.size(); i++) {
//			if (articles.get(i).id == id) {
//				return articles.get(i);
//			}
//		}
//		return null;
//	}
//}
//
//
