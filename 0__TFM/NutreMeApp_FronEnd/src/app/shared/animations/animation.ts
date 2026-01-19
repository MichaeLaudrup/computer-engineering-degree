import { animate, group, query, style, transition, trigger } from "@angular/animations";

export const expandedAnimation = trigger('expandedAnimation', [
    transition(':enter', [
        style({ transform: 'scale(0.1)', opacity: 0}),
        animate('.7s .2s ease-in-out', style({
             transform: 'scale(1)', opacity: 1
        }))
    ])
])

export const delayDesaparition = trigger('delayDesaparition', [
    transition('* <=> *',[
        group([
            query(':enter', [
                style({ opacity: 0 }),
                animate('.7s ease-in-out', style({opacity: 1}))
            ], {optional:true}),
            query(':leave', [
                style({ opacity: 1}),
                animate('.7s ease-in-out', style({opacity: 0}))
            ], {optional:true})
        ])])
])

export const dashboardExpandedAnimation = trigger('dashboardExpandedAnimation', [
    transition(':enter', [
        query('.dashboard__box', [
            style({transform: 'scale(0)'}),
            animate('.2s ease-in-out', style({transform: 'scale(2)'}))
        ], {optional: true})
    ])
])