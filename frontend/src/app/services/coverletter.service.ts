import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CoverletterService {

  private baseUrl = 'http://localhost:8000/';
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(
      private http: HttpClient
  ) { }

  add(newCoverLetter: string): Observable<any> {
    return this.http.post('http://localhost:8000/coverLetter/create', newCoverLetter, {headers: this.headers, responseType: 'text'});
  }

  update(newCoverLetter: string, id: string): Observable<any> {
    let uri = 'http://localhost:8000/coverLetter/update/' + id;
    return this.http.put(uri, newCoverLetter, {headers: this.headers, responseType: 'text'});
  }

  getCoverLetter(id: string): Observable<any> {
    let uri = 'http://localhost:8000/coverLetter/' + id;
    return this.http.get(uri, {headers: this.headers, responseType: 'text'});
  }
}
