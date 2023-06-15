/**
 *
 * @param {import('../../types/day').Day} day
 * @param {{ weekdays: string[], monthsLabels?: string[] }} opts
 */
export function dayTemplate(day: import('../../types/day').Day, { weekdays, monthsLabels }: {
    weekdays: string[];
    monthsLabels?: string[];
}): import("@lion/core").TemplateResult;
