import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private baseUrl = 'http://localhost:8000/';
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});
  private headers_json = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(
    private http: HttpClient
  ) { }

  add(newArticle: string): Observable<any> {
    return this.http.post('http://localhost:8000/article/create', newArticle, {headers: this.headers, responseType: 'text'});
  }

  update(newArticle: string, id: string): Observable<any> {
    let uri = 'http://localhost:8000/article/update/' + id;
    return this.http.put(uri, newArticle, {headers: this.headers, responseType: 'text'});
  }

  getArticle(id: string): Observable<any> {
    let uri = 'http://localhost:8000/article/' + id;
    return this.http.get(uri, {headers: this.headers, responseType: 'text'});
  }

  searchArticle(searchParams: any): Observable<any> {
    let uri = 'http://localhost:8000/article/search'
    return this.http.post(uri, searchParams, {headers: this.headers_json, responseType: 'text'})
  }

  getPDF(id: string): Observable<any> {

    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
       // 'responseType'  : 'blob' as 'json'        //This also worked
    };

    return this.http.get(`http://localhost:8000/getArticle/PDF/${id}`, httpOptions);
  }
}