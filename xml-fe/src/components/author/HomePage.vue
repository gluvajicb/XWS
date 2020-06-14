<template>
    <div>
        <!-- NAVBAR -->
        <div id="authorPageNavbar">
            <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-header">
                        <a class="navbar-brand" v-on:click="redirect"><b>Scientific Articles</b></a>
                    </div>  
                    <ul class="navbar-nav mr-auto">
                        <a class="nav-item nav-link"><h4>Home |</h4></a>
                        <a class="nav-item nav-link"><h4>Advanced Search |</h4></a>
                        <a class="nav-item nav-link"><h4>My Articles |</h4></a>
                        <a class="nav-item nav-link"><h4>Add Article</h4></a>
                    </ul>
                    <div id="logout"><a class="nav-item nav-link"><h4>Logout</h4></a></div>
                </div>
            </nav>
        </div>
        <!-- NAVBAR -->
        

        <!-- Search Component -->
        <div id="searchComponent"> 
            <b-col lg="2">
                <b-input-group>      
                    <b-form-input size="lg" placeholder="Enter Text..." v-model="searchInput"></b-form-input>
                    <span><b-button size="lg">Search</b-button></span>
                </b-input-group>
            </b-col>
        </div>
        <!-- Search Component -->

        
        <div>
            <!-- Ovde ide XML Page component-->
        </div>
    </div>
</template>









<style scoped>

#authorPageNavbar{
    opacity: 0.7;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 999;
    width: 100%;
    height: 23px;
}

#logout{
    float: right;
}

#searchComponent{
    opacity: 0.9;

    position: fixed;
    top: 10%;
    left: 0;
    z-index: 999;
    width: 100%;
    height: 23px;
}

</style>