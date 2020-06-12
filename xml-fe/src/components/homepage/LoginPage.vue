<template>
    <div id="loginForm">
        <b-card-group>
        <b-card border-variant="light" style="max-width: 24rem;" class="mb-2 mx-auto">
            <b-form-group
                label-for="input-username"
            >
                <b-form-input id="input-username" v-model="username" placeholder="Username"></b-form-input>
            </b-form-group>  

            <b-form-group
                label-for="input-pass"
            >
                <b-form-input id="input-pass" type="password" v-model="password" placeholder="Password"></b-form-input>
            </b-form-group>
                    
            <b-button variant="outline-primary" @click="signIn">Sign in</b-button><br><br>
            <b-alert variant="danger" v-model="error" dismissible>{{this.errorMessage}}</b-alert>
        </b-card>
    </b-card-group>
    </div>
</template>

<script>
import { AXIOS } from '../../http-commons'

export default {
    name: 'Login',
    data() {
        return {
            username: '',
            password: '',
            error: null,
            errorMessage: ''
        }
    },
    methods: {
        signIn() {
            this.error = null

            const user = {
                'username': this.username,
                'password': this.password
            }

            AXIOS.post('/auth/login', user)
                .then(response => {
                    if (response.status == 200){
                        console.log(response)
                        localStorage.setItem('token', response.data.accessToken);
                        this.$router.push("/homepage") // KAD SE ULOGUJE - TREBA DA $router.push NA ODREDJENU ADRESU ( U ZAVISNOSTI OD KORISNIKA )
                    } 
                })
                .catch(err => {
                    if (err.response.status == 400) {
                        this.errorMessage = "Wrong username or password!";
                        this.error = true
                    } 
                })
        }
    }
}
</script>

<style scoped>

#loginForm{
  opacity: 0.8;
  display: inline-block;
  
}

</style>