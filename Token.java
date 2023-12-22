public class Token {
    String type;
    String data;

    Integer start_index = 0;
    Token(String type,String data) {
        this.data = data;
        this.type = type;
    }
}
