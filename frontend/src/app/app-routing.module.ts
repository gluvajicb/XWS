import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponentComponent } from './home-component/home-component.component';
import { AddArticleComponent } from './add-article/add-article.component';
import { MyArticlesComponent } from './my-articles/my-articles.component';
import { AssignReviewersComponent } from './assign-reviewers/assign-reviewers.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';


const routes: Routes = [  
  {path: '', component: HomeComponentComponent},
  {path: 'add-article', component: AddArticleComponent},
  {path: 'add-review', component: AddReviewComponent},
  {path: 'my-articles', component: MyArticlesComponent},
  {path: 'assign-reviewers', component: AssignReviewersComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
