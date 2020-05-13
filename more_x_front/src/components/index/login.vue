<template>
  <div class="login-container">
    <div>
      <div class="login-welcome">热爱生活，不断向上</div>
      <button class="login-btn" open-type="getUserInfo" @getuserinfo="getUserInfo">授权登录</button>
    </div>
  </div>
</template>

<script>
  import config from "../../config";

  export default {
    methods: {
      getUserInfo (e) {
        var _this  = this
        let currentUser = e.target.userInfo;
        const loginUrl = config.loginUrl
        wx.login({
          success (res) {
            if (res.code) {
              console.log(res.code)
              wx.request({
                url: loginUrl,
                data: {
                  code: res.code
                },
                success (loginRes){
                  currentUser['openId'] = (JSON.parse(loginRes.data.msg))['openid']
                  console.log(currentUser)
                  wx.setStorage({
                    key:"userinfo",
                    data:currentUser
                  })
                  _this.$emit('loginsuccess')
                }
              })
            } else {
              console.log('登录失败！' + res.errMsg)
            }
          }
        })
      }
    }
  }
</script>

<style lang="scss">
  .login-container{
    align-self: center;
    justify-content: center;
    .login-welcome{
      border: 0px;
      color: #322f3b;//龙葵紫
      background-color: #e2e1e4;
      text-align: center;
      position: relative;
      top: 20rpx;
      z-index: 99;
      width: 80%;
      margin: auto;
      font-weight: bolder;

    }
    .login-btn{
      border-radius: 4px;
      background-color: #e2e1e4;//芡食白
      color: #322f3b;
      font-weight: lighter;
      width: 50vw;
      border: 2px solid #322f3b;
    }
  }

</style>
