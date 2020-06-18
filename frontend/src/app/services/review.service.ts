import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = 'http://localhost:8000/';
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(
    private http: HttpClient
  ) { }

  add(newReview: string): Observable<any> {
    return this.http.post('http://localhost:8000/review/create', newReview, {headers: this.headers, responseType: 'text'});
  }

  getReview(id: string): Observable<any> {
    let uri = 'http://localhost:8000/review/' + id;
    return this.http.get(uri, {headers: this.headers, responseType: 'text'});
  }
}
