<template>
    <div id="registerForm">
        <b-card-group>
            <b-card border-variant="light" style="max-width: 24rem;" class="mb-2 mx-auto">
                <b-form-group label-for="input-username">
                    <b-form-input id="input-username" type="username" v-model="username" placeholder="Username"></b-form-input>
                </b-form-group> 

                <b-form-group label-for="input-password">
                    <b-form-input id="input-password" type="password" v-model="password" placeholder="Password"></b-form-input>
                </b-form-group>

                <b-form-group label-for="input-repeatpassword">
                    <b-form-input id="input-repeatpassword" type="repeatpassword" v-model="repeatpassword" placeholder="Repeat Password"></b-form-input>
                </b-form-group>

                <b-form-group label-for="input-firstname">
                    <b-form-input id="input-firstname" type="firstname" v-model="firstname" placeholder="First Name"></b-form-input>
                </b-form-group>

                <b-form-group label-for="input-lastname">
                    <b-form-input id="input-lastname" type="lastname" v-model="lastname" placeholder="Last Name"></b-form-input>
                </b-form-group>

                <b-form-group label-for="input-email">
                    <b-form-input id="input-email" type="email" v-model="email" placeholder="Email"></b-form-input>
                </b-form-group>
                        
                <b-button variant="outline-primary" @click="register" class="mr-3">Register</b-button>
                <b-button variant="outline-primary" :to="{ path: 'login'}">Sign in</b-button><br><br>
                <b-alert variant="success" v-model="success">Activation link is sent on your email address!</b-alert>
                <b-alert variant="danger" v-model="error" dismissible>{{this.errorMessage}}</b-alert>
                
            </b-card>
        </b-card-group>
    </div>
    
</template>

<script>
export default {
    
}
</script>


<style scoped>


#registerForm{
  opacity: 0.9;
  display: inline-block;
  
}

</style>
