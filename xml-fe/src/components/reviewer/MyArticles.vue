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
                        <a class="nav-item nav-link"><h4>Add Article |</h4></a>
                        <a class="nav-item nav-link"><h4>To Review</h4></a>
                    </ul>
                    <div id="logout"><a class="nav-item nav-link"><h4>Logout</h4></a></div>
                </div>
            </nav>
        </div>
        <!-- NAVBAR -->

        <!-- Secondary Navbar -->
        <div>
            <nav id="secondaryNavbar" class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <ul class="navbar-nav mr-auto">
                        <a class="nav-item nav-link"><h5>Accepted |</h5></a>
                        <a class="nav-item nav-link"><h5>Rejected |</h5></a>
                        <a class="nav-item nav-link"><h5>Revision Required |</h5></a>
                        <a class="nav-item nav-link"><h5>In Review Process |</h5></a>
                        <a class="nav-item nav-link"><h5>Waiting to Be Passed to Reviewers |</h5></a>
                        <a class="nav-item nav-link"><h5>Waiting For Publishers to Process the Reviews |</h5></a>
                        <a class="nav-item nav-link"><h5>Waiting for Publisher to Process the Revision</h5></a>
                    </ul>
                </div>
            </nav>
        </div>
        <!-- Secondary Navbar -->
    
        
        <div>
            <!-- Ovde ide XML Page component-->
        </div>
    </div>
</template>









<style scoped>


#secondaryNavbar{
    opacity: 0.7;
    position: fixed;
    top: 7%;
    left: 0;
    z-index: 999;
    width: 100%;
    height: 60px;
}

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

</style>