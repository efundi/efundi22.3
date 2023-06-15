/**
 * @param {{months: {weeks: {days: import('../../types/day').Day[]}[]}[]}} data
 * @param {{ weekdaysShort: string[], weekdays: string[], monthsLabels?: string[], dayTemplate?: (day: import('../../types/day').Day, { weekdays, monthsLabels }?: any) => import('@lion/core').TemplateResult }} opts
 */
export function dataTemplate(data: {
    months: {
        weeks: {
            days: import('../../types/day').Day[];
        }[];
    }[];
}, { weekdaysShort, weekdays, monthsLabels, dayTemplate }: {
    weekdaysShort: string[];
    weekdays: string[];
    monthsLabels?: string[] | undefined;
    dayTemplate?: ((day: import('../../types/day').Day, { weekdays, monthsLabels }?: any) => import('@lion/core').TemplateResult) | undefined;
}): import("@lion/core").TemplateResult;
