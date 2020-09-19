import { LogPeopleInterface } from './../models/log-people-interface';
import { Observable } from "rxjs";
import { PeopleInterface } from "../models/people-interface";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { delay, take, tap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class PeopleService {
  private serverAPI: string = `${environment.API}/people`;
  private serverAPILog: string = `${environment.API}/log`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<PeopleInterface[]> {
    return this.http
      .get<PeopleInterface[]>(this.serverAPI)
      .pipe(delay(1000), tap(console.log));
  }

  post(people: PeopleInterface): Observable<PeopleInterface> {
    return this.http.post<PeopleInterface>(this.serverAPI, people);
  }

  delete(id: string): Observable<PeopleInterface> {
    return this.http.delete<PeopleInterface>(`${this.serverAPI}/${id}`);
  }

  put(people: PeopleInterface): Observable<PeopleInterface> {
    return this.http.post<PeopleInterface>(this.serverAPI, people);
  }

  getLogs(): Observable<LogPeopleInterface[]> {
    return this.http
      .get<LogPeopleInterface[]>(this.serverAPILog)
      .pipe(delay(1000), tap(console.log));
  }
}
