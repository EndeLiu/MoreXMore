<template>
  <div>
    <div v-if="notLogin" class="login-area">
      <login @loginsuccess="loginSuccess"></login>
    </div>

  </div>
</template>

<script>
import login from "../../components/login";

export default {
  components: {
    login
  },

  data () {
    return {
      notLogin:true
    }
  },

  mounted() {
    if(wx.getStorageSync('userinfo')){
      this.notLogin = false
    } else {
      wx.hideTabBar()
    }
  },

  methods : {
    loginSuccess () {
      this.notLogin = false
      wx.showTabBar()
      wx.showToast({
        title: '登录成功',
        icon: 'success',
        duration: 1000
      })
    }
  },

}
</script>

<style scoped lang="scss">
.login-area{
  height: 100vh;
  display: flex;
  justify-content: center;
  background-color: #e2e1e4;
}
</style>
