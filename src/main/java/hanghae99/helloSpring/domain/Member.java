package hanghae99.helloSpring.domain;

public class Member {

    private Long id;
    // 시스템에 저장을 할때 시스템이 지정하는 Id
    private String name;
    // 가입할떄 사용하는 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
