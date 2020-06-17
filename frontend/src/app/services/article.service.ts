import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private baseUrl = 'http://localhost:8000/';
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(
    private http: HttpClient
  ) { }

  add(newArticle: string): Observable<any> {
    return this.http.post('http://localhost:8000/article/create', newArticle, {headers: this.headers, responseType: 'text'});
  }
}