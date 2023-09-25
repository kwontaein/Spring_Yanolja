package com.example.yanolja.kakao.vo;

public class KakaoVo {

    private long id;
    private String email;
    private String nickname;

    private KakaoVo() {
        // Private 생성자
    }

    public static KakaoDTOBuilder builder() {
        return new KakaoDTOBuilder();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public static class KakaoDTOBuilder {
        private long id;
        private String email;
        private String nickname;

        private KakaoDTOBuilder() {
            // Private 생성자
        }

        public KakaoDTOBuilder id(long id) {
            this.id = id;
            return this;
        }

        public KakaoDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public KakaoDTOBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public KakaoVo build() {
            KakaoVo dto = new KakaoVo();
            dto.id = this.id;
            dto.email = this.email;
            dto.nickname = this.nickname;
            return dto;
        }
    }
}