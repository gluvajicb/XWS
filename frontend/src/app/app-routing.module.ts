import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponentComponent } from './home-component/home-component.component';
import { AddArticleComponent } from './add-article/add-article.component';
import { MyArticlesComponent } from './my-articles/my-articles.component';
import { AssignReviewersComponent } from './assign-reviewers/assign-reviewers.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EditArticleComponent } from './edit-article/edit-article.component';
import { AddCoverletterComponent } from './add-coverletter/add-coverletter.component';
import { SearchComponent } from './search/search.component';


const routes: Routes = [  
  {path: '', component: HomeComponentComponent},
  {path: 'add-article', component: AddArticleComponent},
  {path: 'add-review/:id', component: AddReviewComponent},
  {path: 'my-articles', component: MyArticlesComponent},
  {path: 'assign-reviewers', component: AssignReviewersComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'edit-article/:id', component: EditArticleComponent},
  {path: 'add-coverletter', component: AddCoverletterComponent},
  {path: 'search', component: SearchComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
