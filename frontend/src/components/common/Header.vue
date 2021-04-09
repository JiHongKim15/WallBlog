<template>
  <div>
    <!-- 로그인 모달 -->
    <modal name="my-first-modal">
      <div class="example-modal-window">
        <MyModal></MyModal>
      </div>
    </modal>

    <!-- 네비게이션 -->
    <div class="MyNavigation">
      <div class="widthtest">
        <div
          class="Mycontainer circleBehind"
          style="padding-left:25px; padding-right:25px; padding-top:15px;"
        >
          <img @click="hompage" src="./logo.png" width="100px" style="float:left; cursor:pointer;" />
          <div v-if="this.$session.get('uid') == null">
            <a @click="show" style="float:right;">로그인</a>
          </div>
          <div v-else>
            <a class="menu-trigger" @click="drawer = !drawer" href="#" style="float:right;">
              <span></span>
              <span></span>
              <span></span>
            </a>
          </div>
        </div>
      </div>
    </div>

    <!-- 리스트 -->
    <div v-if="this.$session.get('uid') != null">
      <v-navigation-drawer
        v-model="drawer"
        width="260px"
        temporary
        app
        right
        clipped
        color="#f0b400"
        text-color="#fff"
      >
        <v-list dense>
          <!-- 내정보보기 -->
          <v-list-item link @click="userupdate" style="height:60px;">
            <v-list-item-action>
              <v-icon>mdi-face</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <span>내 정보 보기</span>
            </v-list-item-content>
          </v-list-item>

          <!-- 기업 목록 -->
          <v-list-item link @click="compnaylist" style="height:60px;">
            <v-list-item-action>
              <v-icon>mdi-format-list-bulleted</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <div>
                <span>기업 목록</span>
              </div>
            </v-list-item-content>
          </v-list-item>

          <!-- 포스팅하기 -->
          <v-list-item link @click="createblog" style="height:60px;">
            <v-list-item-action>
              <v-icon>mdi-pencil</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <div>
                <span>포스팅하기</span>
              </div>
            </v-list-item-content>
          </v-list-item>

          <!-- 내가 쓴 글 -->
          <v-list-item link @click="mylist" style="height:60px;">
            <v-list-item-action>
              <v-icon>mdi-book-open</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <div>
                <span>내가 쓴 글 보기</span>
              </div>
            </v-list-item-content>
          </v-list-item>

          <!-- 관리페이지 -->
          <div v-if="this.$session.get('uid') == 'admin'">
            <v-list-item
              link
              @click="admin"
              style="height:60px; position:absolute; bottom:60px; width:100%;"
            >
              <v-list-item-action>
                <v-icon>mdi-cog</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <div>
                  <span>관리페이지</span>
                </div>
              </v-list-item-content>
            </v-list-item>
          </div>

          <v-list-item
            link
            @click="logout"
            style="height:60px; position:absolute; bottom:0px; width:100%;"
          >
            <v-list-item-action>
              <v-icon>mdi-logout-variant</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <div>
                <span>로그아웃</span>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </div>
  </div>
</template>   
<script>
import constants from "../../lib/constants";
import axios from "@/util/axios";
import MyModal from "./modal.vue";
export default {
  components: { MyModal },
  props: {
    source: String,
  },
  data: () => ({
    modal: false,
    test: false,
    drawer: null,
    constants,
    email: "",
    password: "",
    loginData: {
      email: "",
      password: "",
    },
    keyword: "",
  }),

  name: "MyComponent",
  methods: {
    openModal() {
      if (this.modal == true) this.modal = false;
      else this.modal = true;
    },
    closeModal() {
      this.modal = false;
    },
    show() {
      this.$modal.show("my-first-modal");
    },
    hide() {
      this.$modal.hide("my-first-modal");
    },

    mypage: function () {
      axios({
        method: "GET",
        url: "/user/update",
        data: {
          email: this.email,
        },
      })
        .then((Response) => {})
        .catch((ex) => {
          var errorcode = ex.response.status;
          this.$router.push("/errorpage/" + errorcode);
        });
    },
    compnaylist: function () {
      this.$router.push("/companylist");
    },
    userupdate: function () {
      this.$router.push("/user/profile");
    },
    mylist: function () {
      this.$router.push("/blog/list");
    },
    createblog: function () {
      this.$router.push("/blog/blogcreate");
    },
    admin: function () {
      this.$router.push("/admin");
    },
    logout: function () {
      this.$session.destroy();
      this.$router.go();
    },
    search: function () {
      this.$router.push({
        path: "/blog/search",
        query: { keyword: this.keyword },
      });
    },
    hompage: function () {
      this.$router.push("/");
    },
  },
  mount() {
    this.show();
  },
  create() {
    var burger = $(".menu-trigger");

    burger.each(function (index) {
      var $this = $(this);

      $this.on("click", function (e) {
        e.preventDefault();
        $(this).toggleClass("active-" + (index + 1));
      });
    });

    $("#menu-toggle").click(function () {
      $(".main-nav").toggleClass("toggled");
      $(".sticky-nav").toggleClass("toggled");
    });
    $(".nav-menu a").click(function () {
      $(".main-nav").toggleClass("toggled");
      $(".sticky-nav").removeClass("toggled");
    });

    /* random background colors! */
    var back = [
      "salmon",
      "teal",
      "mediumaquamarine",
      "paleturquoise",
      "tomato",
      "sienna",
      "moccasin",
      "coral",
    ];
    var divs = $("div.page");

    $(divs).each(function () {
      var rand = back[Math.floor(Math.random() * back.length)];
      $(this).css("background", rand);
    });

    /* smooth scrolling */
    $(function () {
      $("a[href*=#]:not([href=#])").click(function () {
        if (
          location.pathname.replace(/^\//, "") ==
            this.pathname.replace(/^\//, "") &&
          location.hostname == this.hostname
        ) {
          var target = $(this.hash);
          target = target.length
            ? target
            : $("[name=" + this.hash.slice(1) + "]");
          if (target.length) {
            $("html,body").animate(
              {
                scrollTop: target.offset().top,
              },
              425
            );
            return false;
          }
        }
      });
    });
  },
};
</script>

<style>
.search_main {
  background-color: rgb(236, 190, 248);
}
span {
  margin: 15px;
}
.my-first-modal {
  width: 100px;
}
</style>
