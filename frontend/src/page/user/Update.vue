<template>
  <div>
    <div class="editprofile">
      <!------ Include the above in your HEAD tag ---------->

      <div class="container emp-profile">
        <div class="row">
          <div class="col-md-4">
            <div class="profile-img">
              <v-img
                v-if="imageUrl"
                :src="imageUrl"
                alt
                width="248px"
                height="200px"
                style="left:53px;"
              ></v-img>
              <!-- <img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
                alt
              />-->
              <div class="file btn btn-lg btn-primary">
                Change Photo
                <input
                  type="file"
                  name="file"
                  ref="imageInput"
                  accept="image/*"
                  @change="onChangeImages"
                />
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="profile-head">
              <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                  <a
                    class="nav-link active"
                    id="home-tab"
                    data-toggle="tab"
                    href="#home"
                    role="tab"
                    aria-controls="home"
                    aria-selected="true"
                  >About</a>
                </li>
              </ul>
            </div>
            <div class="tab-content profile-tab" id="myTabContent">
              <div
                class="tab-pane fade show active"
                id="home"
                role="tabpanel"
                aria-labelledby="home-tab"
              >
                <div class="row">
                  <div class="col-md-6">
                    <label>User Id</label>
                  </div>
                  <p>{{user.uid}}</p>
                </div>

                <div class="row">
                  <div class="col-md-6">
                    <label>Email</label>
                  </div>
                  <p>{{user.email}}</p>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <label>Password</label>
                  </div>
                  <input
                    class="input100 mx-1"
                    type="password"
                    id="userpwd"
                    name="userpwd"
                    value="${userinfo}"
                    placeholder="*************"
                    v-model="password"
                    style="border: teal 1px soild;"
                  />
                </div>

                <div class="row">
                  <div class="col-md-6">
                    <label>Password Confirm</label>
                  </div>
                  <input
                    class="input100 mx-1"
                    type="password"
                    id="pwdcheck"
                    name="pwdcheck"
                    placeholder="*************"
                    v-model="passwordcon"
                    style="margin-left:20px"
                  />
                </div>

                <div class="row">
                  <div class="col-md-6">
                    <label>자기소개</label>
                  </div>
                </div>
                <div class="row">
                  <b-form-textarea
                    id="introduce"
                    v-model="introduce"
                    placeholder="Enter something..."
                    rows="3"
                    style="border: none;"
                  ></b-form-textarea>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-2">
            <input
              type="button"
              class="profile-edit-btn"
              name="btnAddMore"
              @click="update"
              value="수정완료"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";
import constants from "../../lib/constants";
export default {
  name: "SignupRequest",
  data() {
    return {
      user: {},
      password: "",
      introduce: "",
      passwordcon: "",
      text: "",
      image: "",
      imageUrl: null,
      newfilename: "",
      imgok: false,
    };
  },
  methods: {
    update: function () {
      if (this.password === "") {
        alert("비밀번호를 입력하세요");
      } else if (this.password !== this.passwordcon) {
        alert("비밀번호가 다릅니다.");
      } else {
        // var file = this.$refs.image.files[0];
        // console.log(this.file);
        if (this.imgok == true) {
          const file = this.$refs.imageInput.files;
          this.file = file.length > 0 ? file[0] : null;

          const formData = new FormData();
          formData.append("profileImg", this.file);

          axios
            .post("/img", formData, {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            })
            .then((res) => {
              this.newfilename = res.data;

              axios({
                method: "PUT",
                url: "/user",
                data: {
                  uid: this.user.uid,
                  password: this.password,
                  email: this.user.email,
                  introduce: this.introduce,
                  image: this.newfilename,
                },
              })
                .then((Response) => {
                  alert("수정이 완료되었습니다.");
                  this.$router.push("/user/profile");
                })
                .catch((ex) => {
                  var errorcode = ex.response.status;
                  this.$router.push("/errorpage/" + errorcode);
                });
            })
            .catch((err) => {
              console.log(err);
            });
        } else {
          axios({
            method: "PUT",
            url: "/user",
            data: {
              uid: this.user.uid,
              password: this.password,
              email: this.user.email,
              introduce: this.introduce,
              image: "기본.jpg",
            },
          })
            .then((Response) => {
              alert("수정이 완료되었습니다.");
              this.$router.push("/user/profile");
            })
            .catch((ex) => {
              var errorcode = ex.response.status;
              this.$router.push("/errorpage/" + errorcode);
            });
        }
      }
    },
    onChangeImages(e) {
      this.imgok = true;
      const file = e.target.files[0];
      this.imageUrl = URL.createObjectURL(file); // Create File URL
    },
  },
  mounted() {
    var uid = this.$session.get("uid");
    axios
      .post("/user/update", uid)
      .then((Response) => {
        this.user = Response.data;
        this.imageUrl = 'http://i3c201.p.ssafy.io/api/img?profileImgName=' + this.user.image;
      })
      .catch((ex) => {
        var errorcode = ex.response.status;
        this.$router.push("/errorpage/" + errorcode);
      });
  },
};
</script>
