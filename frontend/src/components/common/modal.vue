<template>
  <transition name="modal" appear>
    <div class="login_main">
      <!-- LOGIN MODULE -->
      <div class="login">
        <div class="wrap">
          <!-- TOGGLE -->
          <div id="toggle-wrap">
            <div id="toggle-terms">
              <div id="cross">
                <span></span>
                <span></span>
              </div>
            </div>
          </div>
          <!-- TERMS -->
          <div class="terms">
            <h2>SSAFY ESACAPE NO.1</h2>
            <br />
            <br />
            <h4>가. 개인정보의 수집 및 이용 목적</h4>
            <p>① 국가공간정보포털은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.</p>
            <p>1. 국가공간정보포털 서비스 제공을 위한 회원관리</p>
            <p>
              1) 공간정보 다운로드, 오픈API 신청 및 활용 등 포털 서비스 제공과 서비스 부정이용 방지를 목적으로 개인정보를
              처리합니다.
            </p>
            <br />
            <h4>나. 수집하는 개인정보의 항목</h4>
            <p>① 국가공간정보포털 회원정보(필수): 이름, 이메일(아이디), 비밀번호</p>
            <br />

            <h4>다. 개인정보의 보유 및 이용기간</h4>
            <p>① 국가공간정보포털은 법령에 따른 개인정보 보유ㆍ이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의 받은 개인정보 보유ㆍ이용기간 내에서 개인정보를 처리ㆍ보유합니다.</p>
            <p>1. 국가공간정보포털 회원정보</p>
            <p>- 수집근거: 정보주체의 동의</p>
            <p>- 보존기간: 회원 탈퇴 요청 전까지(1년 경과 시 재동의)</p>
            <p>- 보존근거: 정보주체의 동의</p>
            <br />

            <h4>라. 동의 거부 권리 및 동의 거부에 따른 불이익</h4>
            <p>위 개인정보의 수집 및 이용에 대한 동의를 거부할 수 있으나, 동의를 거부할 경우 회원 가입이 제한됩니다.</p>
            <br />
            <br />
            <label style="margin-left: 33%;">
              <input v-model="isTerm" type="checkbox" id="term" style="margin-right:10px;" />
              <span>약관에 동의합니다</span>
            </label>
          </div>

          <!-- RECOVERY -->
          <div class="recovery">
            <h2>비밀번호 찾기</h2>
            <p>
              <strong>Email</strong>을 입력하신 후
              <strong>Submit</strong>을 눌러주세요
            </p>
            <p>
              <Strong>비밀번호</Strong>를
              <strong>Email</strong>로 보내드립니다.
            </p>
            <form class="recovery-form" action method="post">
              <input
                type="text"
                class="input"
                id="user_recover"
                placeholder="Enter Email"
                v-model="passwordrecovery"
              />
              <input type="button" class="button" value="Submit" @click="passwordrecoveryaction" />
            </form>
            <p class="mssg">비밀번호를 Email로 전송하였습니다.</p>
          </div>

          <!-- SLIDER -->
          <div class="content">
            <!-- LOGO -->
            <div class="logo">
              <a href="#">
                <img
                  src="http://res.cloudinary.com/dpcloudinary/image/upload/v1506186248/logo.png"
                  alt
                />
              </a>
            </div>
            <!-- SLIDESHOW -->
            <div id="slideshow" style="z-index:1;">
              <div class="one">
                <h2>
                  <span>환영합니다</span>
                </h2>
                <p>취뽀를 위한 정보를 얻어가세요</p>
              </div>
              <div class="two">
                <h2>
                  <span>편리합니다</span>
                </h2>
                <p>기업 검색만으로도 당신이 필요한 정보를 얻을 수 있습니다.</p>
              </div>
              <div class="three">
                <h2>
                  <span>공유하세요</span>
                </h2>
                <p>다른 취준생들과 직무 기업에 대한 정보를 공유하세요</p>
              </div>
              <div class="four">
                <h2>
                  <span>피드백을 주세요</span>
                </h2>
                <p>좋아요와 댓글로 정보의 신뢰감과 질은 높아집니다.</p>
              </div>
            </div>
          </div>
          <!-- LOGIN FORM -->
          <div class="user">
            <div class="form-wrap">
              <!-- TABS -->
              <div class="tabs">
                <h3 class="login-tab">
                  <a class="log-in active" href="#login-tab-content">
                    <span>Login</span>
                  </a>
                </h3>
                <h3 class="signup-tab">
                  <a class="sign-up" href="#signup-tab-content">
                    <span>Sign Up</span>
                  </a>
                </h3>
              </div>
              <!-- TABS CONTENT -->
              <div class="tabs-content">
                <!-- TABS CONTENT LOGIN -->
                <div id="login-tab-content" class="active">
                  <form class="login-form" action method="post">
                    <input
                      type="text"
                      :class="[email_validation ? 'ok' : 'input']"
                      id="user_login"
                      autocomplete="off"
                      placeholder="Email"
                      v-model="loginData.email"
                    />
                    <input
                      type="password"
                      id="user_pass"
                      autocomplete="off"
                      placeholder="Password"
                      v-model="loginData.password"
                      :class="[space ? 'ok' : 'input']"
                    />
                    <!-- <input type="checkbox" class="checkbox" checked id="remember_me" /> -->
                    <!-- <label for="remember_me">Remember me</label> -->
                    <input
                      type="button"
                      @click="signin"
                      value="Login"
                      :class="[this.login1 == true && this.login2 == true? 'button' : 'not']"
                      :disabled="this.emailbutton == false"
                    />
                  </form>
                  <div class="help-action">
                    <p>
                      <i class="fa fa-arrow-left" aria-hidden="true"></i>
                      <a class="forgot" href="#">Forgot your password?</a>
                    </p>
                  </div>
                </div>
                <!-- 회원가입 -->
                <!-- TABS CONTENT SIGNUP -->
                <div id="signup-tab-content">
                  <form class="signup-form" action method="post">
                    <input
                      id="user_email"
                      placeholder="Email"
                      v-model="Emailcheck.email"
                      :class="[email_validation_up ? 'ok' : 'input']"
                    />
                    <input
                      v-model="Emailcheck.uid"
                      type="text"
                      id="user_name"
                      autocomplete="off"
                      placeholder="Username"
                      :class="[space_up ? 'ok' : 'input']"
                    />
                    <input
                      v-model="Emailcheck.password"
                      type="password"
                      id="user_pass"
                      autocomplete="off"
                      placeholder="Password"
                      :class="[space_uppw ? 'ok' : 'input']"
                    />
                    <input
                      v-model="Emailcheck.passwordConfirm"
                      type="password"
                      id="user_pass"
                      autocomplete="off"
                      placeholder="Confirm Password"
                      :class="[passwordconfrimcheck ? 'ok' : 'input']"
                    />
                    <div style="float: left; margin-right:10px">
                      <input
                        type="button"
                        class="button"
                        @click="emailchecking"
                        value="Email 인증"
                        :class="[this.emailokbutton? 'button' : 'not']"
                        :disabled="this.emailokbutton == false"
                      />
                    </div>
                    <input
                      type="button"
                      class="button"
                      @click="signup"
                      value="Sign Up"
                      :class="[this.up1 == true && this.up2 == true && this.up3 == true && this.up4 == true && this.isTerm == true? 'button' : 'not']"
                      :disabled="this.signbutton && this.isTerm == false"
                    />
                  </form>

                  <div class="help-action">
                    <p>
                      <i class="fa fa-arrow-left" aria-hidden="true"></i>
                      <a class="agree" href="#">약관보기</a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import constants from "../../lib/constants";
import axios from "@/util/axios";
export default {
  data() {
    return {
      emailcheck: false,
      isTerm: false,
      loginData: {
        email: "",
        password: "",
      },
      Emailcheck: {
        uid: "",
        email: "",
        password: "",
        passwordConfirm: "",
      },
      passwordrecovery: "",
      emailbutton: false,
      login1: false,
      login2: false,
      up1: false,
      up2: false,
      up3: false,
      up4: false,
      signbutton: false,
      emailokbutton: false,
    };
  },
  methods: {
    signin: function () {
      axios({
        method: "POST",
        url: "/login",
        data: {
          password: this.loginData.password,
          email: this.loginData.email,
        },
      })
        .then((Response) => {
          this.$session.set("uid", Response.data);
          this.$modal.hide("my-first-modal");
          this.$router.go();
        })
        .catch((ex) => {
          var errorcode = ex.response.status;
          if (errorcode == 400) {
            alert("로그인에 실패하였습니다.");
          } else {
            this.$router.push("/errorpage/" + errorcode);
          }
        });
    },

    emailchecking: function () {
      this.emailcheck = true;
      axios({
        method: "POST",
        url: "/user/emailsend",
        data: {
          to: this.Emailcheck.email,
        },
      })
        .then((Response) => {
          alert("이메일을 확인해주세요");
        })
        .catch((ex) => {
          var errorcode = ex.response.status;
          if (errorcode == 418) {
            alert("Email을 확인하세요!");
          } else if (errorcode == 409) {
            alert("이미 가입된 이메일입니다");
          } else {
            this.$router.push("/errorpage/" + errorcode);
          }
        });
    },
    signup: function () {
      this.$modal.show("emailcheckmodal");
      if (this.isTerm === false) {
        alert("약관 동의에 체크해주세요");
      } else if (this.Emailcheck.uid == "admin") {
        alert("ADMIN은 사용이 불가능 합니다");
      } else {
        axios
          .post("/user", this.Emailcheck)
          .then((res) => {
            alert("회원가입이 완료되었습니다.");
          })
          .catch((ex) => {
            var errorcode = ex.response.status;
            if (errorcode == 418) {
              alert("Email을 확인하세요!");
            } else if (errorcode == 409) {
              alert("이미 가입된 이메일입니다");
            } else if (errorcode == 424) {
              alert("이미 존재하는 이름입니다");
            } else {
              this.$router.push("/errorpage/" + errorcode);
            }
          });
      }
    },
    passwordrecoveryaction: function () {
      axios({
        method: "POST",
        url: "/user/searchPw",
        data: {
          email: this.passwordrecovery,
        },
      })
        .then((res) => {})
        .catch((ex) => {
          var errorcode = ex.response.status;
          if (errorcode == 418) {
            alert("가입되지 않은 이메일입니다.");
          } else this.$router.push("/errorpage/" + errorcode);
        });
    },
  },
  computed: {
    email_validation() {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (re.test(this.loginData.email)) {
        this.login1 = true;
      } else {
        this.login1 = false;
      }
      if (this.login1 == true && this.login2 == true) this.emailbutton = true;
      else this.emailbutton = false;
      return re.test(this.loginData.email);
    },
    email_validation_up() {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (re.test(this.Emailcheck.email)) {
        this.up1 = true;
        this.emailokbutton = true;
      } else {
        this.up1 = false;
        this.emailokbutton = false;
      }
      if (
        this.up1 == true &&
        this.up2 == true &&
        this.up3 == true &&
        this.up4 == true
      ) {
        this.signbutton = true;
      } else this.signbutton = false;
      return re.test(this.Emailcheck.email);
    },
    space() {
      if (this.loginData.password != "") {
        this.login2 = true;
        if (this.login1 == true && this.login2 == true) this.emailbutton = true;
        return true;
      } else this.login2 = false;
    },
    space_up() {
      
      if (this.Emailcheck.password != "") {
        this.up2 = true;
        if (
          this.up1 == true &&
          this.up2 == true &&
          this.up3 == true &&
          this.up4 == true
        ) {
          this.signbutton = true;
        } else this.signbutton = false;
        return true;
      } else this.up2 = false;
    },
    space_uppw() {
      if ( this.Emailcheck.password != "") {
        this.up3 = true;
        if (
          this.up1 == true &&
          this.up2 == true &&
          this.up3 == true &&
          this.up4 == true
        ) {
          this.signbutton = true;
        } else this.signbutton = false;
        return true;
      } else this.up3 = false;
    },
    passwordconfrimcheck() {
      if (this.Emailcheck.passwordConfirm != "") {
        if (this.Emailcheck.password === this.Emailcheck.passwordConfirm) {
          this.up4 = true;
          if (
            this.up1 == true &&
            this.up2 == true &&
            this.up3 == true &&
            this.up4 == true
          ) {
            this.signbutton = true;
          } else {
            this.signbutton = false;
            this.up4 = false;
          }
          return true;
        } else {
          this.signbutton = false;
          this.up4 = false;
        }
      } else this.up4 = false;
    },
  },
  created() {
    // LOGIN TABS
    $(function () {
      var tab = $(".tabs h3 a");
      tab.on("click", function (event) {
        event.preventDefault();
        tab.removeClass("active");
        $(this).addClass("active");
        var tab_content = $(this).attr("href");
        $('div[id$="tab-content"]').removeClass("active");
        $(tab_content).addClass("active");
      });
    });

    // SLIDESHOW
    $(function () {
      $("#slideshow > div:gt(0)").hide();
      setInterval(function () {
        $("#slideshow > div:first")
          .fadeOut(1000)
          .next()
          .fadeIn(1000)
          .end()
          .appendTo("#slideshow");
      }, 3850);
    });

    // CUSTOM JQUERY FUNCTION FOR SWAPPING CLASSES
    (function ($) {
      "use strict";
      $.fn.swapClass = function (remove, add) {
        this.removeClass(remove).addClass(add);
        return this;
      };
    })(jQuery);

    // SHOW/HIDE PANEL ROUTINE (needs better methods)
    // I'll optimize when time permits.
    $(function () {
      $(".agree,.forgot, #toggle-terms, .log-in, .sign-up").on(
        "click",
        function (event) {
          event.preventDefault();
          var terms = $(".terms"),
            recovery = $(".recovery"),
            close = $("#toggle-terms"),
            arrow = $(".tabs-content .fa");
          if (
            $(this).hasClass("agree") ||
            $(this).hasClass("log-in") ||
            ($(this).is("#toggle-terms") && terms.hasClass("open"))
          ) {
            if (terms.hasClass("open")) {
              terms.swapClass("open", "closed");
              close.swapClass("open", "closed");
              arrow.swapClass("active", "inactive");
            } else {
              if ($(this).hasClass("log-in")) {
                return;
              }
              terms.swapClass("closed", "open").scrollTop(0);
              close.swapClass("closed", "open");
              arrow.swapClass("inactive", "active");
            }
          } else if (
            $(this).hasClass("forgot") ||
            $(this).hasClass("sign-up") ||
            $(this).is("#toggle-terms")
          ) {
            if (recovery.hasClass("open")) {
              recovery.swapClass("open", "closed");
              close.swapClass("open", "closed");
              arrow.swapClass("active", "inactive");
            } else {
              if ($(this).hasClass("sign-up")) {
                return;
              }
              recovery.swapClass("closed", "open");
              close.swapClass("closed", "open");
              arrow.swapClass("inactive", "active");
            }
          }
        }
      );
    });

    // DISPLAY MSSG
    $(function () {
      $(".recovery .button").on("click", function (event) {
        event.preventDefault();
        $(".recovery .mssg").addClass("animate");
        setTimeout(function () {
          $(".recovery").swapClass("open", "closed");
          $("#toggle-terms").swapClass("open", "closed");
          $(".tabs-content .fa").swapClass("active", "inactive");
          $(".recovery .mssg").removeClass("animate");
        }, 2500);
      });
    });

    // DISABLE SUBMIT FOR DEMO
    $(function () {
      $(".button").on("click", function (event) {
        $(this).stop();
        r;
        event.preventDefault();
        return false;
      });
    });
  },
  mounted() {},
};
</script>

<style>
</style>
