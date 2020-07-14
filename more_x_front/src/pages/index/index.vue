<template>
  <div>
    <div v-if="notLogin" class="login-area">
      <login @loginsuccess="loginSuccess"></login>
    </div>
    <div v-else>
      <div class="index-title"></div>
      <div>
        <accounts ref="accounts"></accounts>
      </div>
    </div>


  </div>
</template>

<script>
import login from "../../components/index/login";
import accounts from  "../../components/index/ accounts"
import config from "../../config";

export default {
  components: {
    login,
    accounts
  },

  data () {
    return {
      notLogin:true
    }
  },

  onLoad (option) {
    if(option.logout === 'true'){
      this.notLogin = true
    }
  },

  mounted() {
    console.log('挂载')
    if(wx.getStorageSync('userinfo')){
      this.notLogin = false
      this.getRecord()
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
      this.getRecord()
    },

    getRecord () {
      var _this = this
      wx.request({
        url: config.accountUrl + '/all',
        data: _this.accountItem,
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success (res) {
          console.log('get record success')
          console.log(res.data)
          _this.$refs.accounts.items = res.data.reverse()
          _this.$refs.accounts.processItemInfo()
        }
      })
    }
  },

}
</script>

<style scoped lang="scss">
.index-title {
  height: 15vh;
  background-color: #322f3b;
  margin-bottom: 10px;
}

.login-area{
  height: 100vh;
  display: flex;
  justify-content: center;
  background-color: #e2e1e4;
}

</style>
