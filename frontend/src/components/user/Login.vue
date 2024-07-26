<template>
<v-responsive class="border rounded">
  <v-app>
    <Navbar />
    <v-main class="contentArea">
      <v-container>
        <v-card
          class="mx-auto loginForm"
        >
        <v-text-field
          v-model="id"
          :readonly="loading"
          :rules="[required]"
          class="mt-10"
          label="id"
          clearable
        ></v-text-field>

        <v-text-field
          v-model="password"
          :readonly="loading"
          :rules="[required]"
          label="Password"
          placeholder="Enter your password"
          clearable
        ></v-text-field>

        <v-col cols="auto">
          <v-btn 
            size="large"
            color="success"
            class="mt-5"
          >sign in</v-btn>
        </v-col>
  
          <div class="socialLoginBtns">
            <img class="socialLoginBtn" id="kakaoLoginBtn" src="@/images/user/kakao_login.png">
            <img class="socialLoginBtn" id="googleLoginBtn" src="@/images/user/google_login.png">
            <img class="socialLoginBtn" id="naverLoginBtn" src="@/images/user/naver_login.png">
          </div>
        </v-card>
      </v-container>
    </v-main>
  </v-app>
</v-responsive>
</template>
<style scoped>
  .contentArea {
    height: 100%;
    width: 100%;
    background-color: rgb(var(--v-theme-lightBeige));
  }
  
  .loginForm {
    min-height: 600px;
    max-width: 400px;
    margin-top: 130px;
    border: 10px dashed rgb(var(--v-theme-lightPurple));
    background-color: rgb(var(--v-theme-lightBeige));
  }
  
  .v-input {
    width: 300px;
    margin-left: 40px;
  }
  
  .v-btn {
    width: 300px;
    margin-left: 25px;
  }
  #kakaoLoginBtn {
    margin-top: 10px;
  }
  
  .socialLoginBtns {
    position: relative;
    top: 40px;
  }
  
  .socialLoginBtn {
    width: 200px;
    margin-top: 10px;
    margin-left: 25%;
  }
  </style>