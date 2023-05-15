import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs/internal/Subscription';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/services/bolnica.service';
import { BolnicaDialogComponent } from '../dialogs/bolnica-dialog/bolnica-dialog.component';

@Component({
  selector: 'app-bolnica',
  templateUrl: './bolnica.component.html',
  styleUrls: ['./bolnica.component.css']
})
export class BolnicaComponent {

  constructor (private bolnicaService: BolnicaService, private dialog: MatDialog){}

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa','budzet', 'actions'];
  dataSource!: MatTableDataSource<Bolnica>;

  ngOnInit(){
    this.loadData();
  }

  loadData(){
    this.bolnicaService.getAllBolnica().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        console.log(error.name + ' ' + error.message);
      
      }
      );
  }

  public openDialog(flag: number, bolnica?: Bolnica): void {
    const dialogRef = this.dialog.open(BolnicaDialogComponent, {data: (bolnica?bolnica: new Bolnica())});
    dialogRef.componentInstance.flagBolDialog= flag;
    dialogRef.afterClosed().subscribe(res=> {if(res==1) this.loadData();})
  }

  ngOnDestroy(): void {this.subscription.unsubscribe();}
  ngOnChanges(){this.loadData();}
}
